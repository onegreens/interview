package com.cl.interview.service.impl;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.common.Page;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.dao.BookChapterDao;
import com.cl.interview.dao.BookDao;
import com.cl.interview.dto.BookChapterDto;
import com.cl.interview.entity.BookChapterEntity;
import com.cl.interview.po.BookChapterPo;
import com.cl.interview.service.BookChapterService;
import com.cl.interview.util.DaoUtil;
import com.cl.interview.util.SerializableFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Transactional
@Service("bookChapterService")
public class BookChapterServiceImpl implements BookChapterService {

    private static List<BookChapterPo> posCache;

    @Autowired
    BookChapterDao dao;

    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    @Override
    public List<BookChapterPo> findAll() {
        return DaoUtil.convertDataList(dao.findAll());
    }

    @Override
    public BookChapterPo save(BookChapterPo obj) {
//        if (obj.getId() == null || obj.getId() == 0)
//            obj.setId(IdGenerator.nextId());
        if (obj.getParent() == null) {
            obj.setParent(new BookChapterPo(0));
        } else if (obj.getParent().getId() == null) {
            obj.getParent().setId(0);
        }
        if (obj.getLevel() == null) {
            if (obj.getParent().getLevel() == null) {
                obj.setLevel(1);
            } else {
                BookChapterPo parent = getOne(obj.getParent().getId());
                obj.setLevel(parent.getLevel() + 1);
            }
        }
        obj.setCreateTime(Calendar.getInstance().getTime());
        BookChapterEntity entity = obj.toObject();
        return dao.save(entity).toObject();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
        this.refreshCache();
    }

    @Override
    public BookChapterPo getOne(Integer id) {
        return dao.getOne(id).toObject();
    }

    @Override
    public void delete(Iterable<BookChapterPo> entities) {
        if (entities != null) {
            for (BookChapterPo po : entities) {
                dao.delete(po.toObject());
            }
        }

        this.refreshCache();
    }

    @Override
    public HttpResp create(BookChapterDto dto) {
        HttpResp resp = new HttpResp();

        save(dto.toObject());

        this.refreshCache();
        return resp;
    }

    @Override
    public HttpResp update(BookChapterDto dto) {
        // TODO Auto-generated method stub
        HttpResp resp = new HttpResp();

        BookChapterPo po = getOne(dto.getId());
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("编辑习题信息失败，习题信息不存在");
            return resp;
        }
        save(dto.toObject());

        this.refreshCache();
        return resp;
    }

    @Override
    public String doSerializable() {
        String fileName = this.getClass().getSimpleName();
        String filePath = config.getWebFilePath();
        List<BookChapterPo> list = this.findAll();
        try {
            SerializableFile.doSerializable(list, filePath + File.separator + fileName + ".out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveByFile(MultipartFile mf) {
        if (mf != null) {
            List<BookChapterPo> list = null;
            try {
                list = (List<BookChapterPo>) SerializableFile.upSerializable(mf.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (list != null) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    BookChapterPo po = list.get(i);
                    boolean add = true;
                    if (po.getId() != null) {
                        BookChapterPo old = getOne(po.getId());
                        if (old != null) {
                            add = false;
                        }
                    }
                    if (add && po.getName() != null) {
                        save(po);
                    }
                }

                this.refreshCache();
            }

        }
    }

    private void refreshCache() {
        posCache = null;
    }

    @Override
    public List<Map> treeData() {
        if (posCache == null) {
            posCache = DaoUtil.convertDataList(this.dao.findByParentId(null));
        }
        return toMap(posCache);
    }


    public List<Map> toMap(List<BookChapterPo> pos) {
        if (pos == null)
            return null;
        List<Map> list = new ArrayList<>();
        for (int i = 0, size = pos.size(); i < size; i++) {
            BookChapterPo po = pos.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("value", po.getId());
            map.put("label", po.getName());
            if (po.getChildren() != null && po.getChildren().size() > 0) {
                map.put("children", toMap(po.getChildren()));
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public Page getDataByPage(int pageNo, int pageSize, BookChapterPo obj, List<String> orderBysList, String search) {

        StringBuffer hql = new StringBuffer(" from BookChapterEntity entity ");
        Map<String, Object> params = new HashMap<String, Object>();
        String orderBy = "";
        if (orderBysList != null && orderBysList.size() > 0) {
            for (String i : orderBysList) {
                orderBy = i;
            }
        }
        if (obj != null) {
            hql.append(getWhereSql(obj));
            params = getWhereParam(obj);
        }
        if (search != null && search.length() > 0) {
            hql.append("and entity.name like :search");
            params.put("search", "%" + search + "%");
        }
        String queryHql = hql.toString();

        if (orderBy.length() > 0) {
            queryHql += " order by " + orderBy + " desc ";
        }

        Page resultPage = baseDao.pagedQuery(queryHql, pageNo, pageSize, params);
        List<BookChapterEntity> list = (List<BookChapterEntity>) resultPage.getResult();
        resultPage.setResult(DaoUtil.convertDataList(list));
        return resultPage;
    }

    @Transient
    public Map<String, Object> getWhereParam(BookChapterPo t) {
        Map<String, Object> params = new HashMap<String, Object>();

        return params;

    }

    @Transient
    public String getWhereSql(BookChapterPo t) {
        StringBuffer sb = new StringBuffer("where 1 = 1 and entity.parent is null");
        return sb.toString();
    }
}
