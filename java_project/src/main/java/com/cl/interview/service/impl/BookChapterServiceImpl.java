package com.cl.interview.service.impl;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.common.MDData;
import com.cl.interview.common.Page;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.dao.BookChapterDao;
import com.cl.interview.dao.BookDao;
import com.cl.interview.dto.BookChapterDto;
import com.cl.interview.entity.BookChapterEntity;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.po.BookChapterPo;
import com.cl.interview.service.BookChapterService;
import com.cl.interview.service.BookContentService;
import com.cl.interview.util.ClassUtils;
import com.cl.interview.util.DaoUtil;
import com.cl.interview.util.SerializableFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    BookContentService contentService;
    @Autowired
    BookChapterDao dao;
    @Autowired
    BookDao bookDao;

    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    public List<MDData> toMD(String bookId) {
        List<BookChapterEntity> list = this.dao.findByBookIdAndLevel(bookId, 1);
        List<MDData> result = new ArrayList<>();
        if (list != null) {
            for (BookChapterEntity entity :
                    list) {
                result.add(toMD(entity));
            }
        }
        return result;
    }

    public MDData toMD(BookChapterEntity entity) {
        MDData data = new MDData(entity.getName(), entity.getLevel());
        List<MDData> result = contentService.toMD(entity.getId());
        if (entity.getChildren() != null) {
            for (BookChapterEntity e :
                    entity.getChildren()) {
                result.add(toMD(e));
            }
        }
        data.setList(result);
        return data;
    }

    @Override
    public List<BookChapterPo> findAll() {
        return DaoUtil.convertDataList(dao.findAll());
    }

    @Override
    public BookChapterPo save(BookChapterPo obj) {
        BookChapterEntity entity = obj.toObject();
        return dao.save(entity).toObject();
    }

    private void format(BookChapterDto obj) throws ArgumentException {
        if (obj.getLevel() == null) {
            if (StringUtils.isEmpty(obj.getParentId())) {

                obj.setLevel(1);
            } else {
                BookChapterPo parent = getOne(obj.getParentId());
                obj.setLevel(parent.getLevel() + 1);
            }
        }
        if (StringUtils.isEmpty(obj.getId())) {
            obj.setCreateTime(Calendar.getInstance().getTime());
        } else {
            if (obj.getId().equals(obj.getParentId())) {
                throw new ArgumentException("不能选择自身作为父级");
            }
        }
        if (StringUtils.isEmpty(obj.getBookId())) {
            throw new ArgumentException("书籍不能为空");
        }
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
        this.refreshCache();
    }

    @Override
    public BookChapterPo getOne(String id) {
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
    public HttpResp create(BookChapterDto dto) throws ArgumentException {
        HttpResp resp = new HttpResp();
        format(dto);
        save(dto.toObject());
        this.refreshCache();
        return resp;
    }

    @Override
    public HttpResp update(BookChapterDto dto) throws ArgumentException {
        // TODO Auto-generated method stub
        HttpResp resp = new HttpResp();
        format(dto);
        BookChapterPo po = getOne(dto.getId());
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("编辑习题信息失败，习题信息不存在");
            return resp;
        }
        save((BookChapterPo) ClassUtils.inheritValue(po, dto, null));
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
                        if (po.getId().equals(po.getParentId())) {
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

    public void refreshCache() {
        posCache = null;
    }

    @Override
    public List<Map> treeData(String bookId) {
        if (posCache == null) {
            posCache = DaoUtil.convertDataList(this.dao.findByParentId(null));
        }
        return toMap(posCache, bookId);
    }


    public List<Map> toMap(List<BookChapterPo> pos, String bookId) {
        if (pos == null)
            return null;
        List<Map> list = new ArrayList<>();
        if (StringUtils.isEmpty(bookId)) {
            return list;
        }
        for (int i = 0, size = pos.size(); i < size; i++) {
            BookChapterPo po = pos.get(i);
            if (bookId.equals(po.getBookId())) {
                Map<String, Object> map = new HashMap<>();
                map.put("value", po.getId());
                map.put("label", po.getName());
                if (po.getChildren() != null && po.getChildren().size() > 0) {
                    map.put("children", toMap(po.getChildren(), bookId));
                }
                list.add(map);
            }

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
            queryHql += " order by sort desc ";
        } else {
            queryHql += " order by sort asc ";
        }

        Page resultPage = baseDao.pagedQuery(queryHql, pageNo, pageSize, params);
        List<BookChapterEntity> list = (List<BookChapterEntity>) resultPage.getResult();
        resultPage.setResult(DaoUtil.convertDataList(list));
        return resultPage;
    }

    @Transient
    public Map<String, Object> getWhereParam(BookChapterPo t) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(t.getBookId()))
            params.put("bookId", t.getBookId());
        return params;

    }

    @Transient
    public String getWhereSql(BookChapterPo t) {
        StringBuffer sb = new StringBuffer("where 1 = 1 and entity.parentId is null");
        if (!StringUtils.isEmpty(t.getBookId()))
            sb.append(" and bookId = :bookId");
        return sb.toString();
    }
}
