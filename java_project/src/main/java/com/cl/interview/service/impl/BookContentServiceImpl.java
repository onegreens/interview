package com.cl.interview.service.impl;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IdGenerator;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.common.Page;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.dao.BookContentDao;
import com.cl.interview.dto.BookContentDto;
import com.cl.interview.entity.BookContentEntity;
import com.cl.interview.po.BookContentPo;
import com.cl.interview.service.BookContentService;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@Service("bookContentService")
public class BookContentServiceImpl implements BookContentService {

    @Autowired
    BookContentDao dao;

    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    @Override
    public List<BookContentPo> findAll() {
        return DaoUtil.convertDataList(dao.findAll());
    }

    @Override
    public BookContentPo save(BookContentPo obj) {
        obj.setCreateTime(Calendar.getInstance().getTime());
        return dao.save(obj.toObject()).toObject();
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public BookContentPo getOne(String id) {
        return dao.getOne(id).toObject();
    }

    @Override
    public void delete(Iterable<BookContentPo> entities) {
        if (entities != null) {
            for (BookContentPo po : entities) {
                dao.delete(po.toObject());
            }
        }
    }

    @Override
    public HttpResp create(BookContentDto dto) {
        HttpResp resp = new HttpResp();

        save(dto.toObject());

        return resp;
    }

    @Override
    public HttpResp update(BookContentDto dto) {
        // TODO Auto-generated method stub
        HttpResp resp = new HttpResp();

        BookContentPo po = getOne(dto.getId());
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("编辑习题信息失败，习题信息不存在");
            return resp;
        }
        save(dto.toObject());

        return resp;
    }

    @Override
    public String doSerializable() {
        String fileName = this.getClass().getSimpleName();
        String filePath = config.getWebFilePath();
        List<BookContentPo> list = this.findAll();
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
            List<BookContentPo> list = null;
            try {
                list = (List<BookContentPo>) SerializableFile.upSerializable(mf.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (list != null) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    BookContentPo po = list.get(i);
                    boolean add = true;
                    if (po.getId() != null) {
                        BookContentPo old = getOne(po.getId());
                        if (old != null) {
                            add = false;
                        }
                    }
                    if (add ) {
                        save(po);
                    }
                }
            }
        }
    }

    @Override
    public Page getDataByPage(int pageNo, int pageSize, BookContentPo obj, List<String> orderBysList, String search) {

        StringBuffer hql = new StringBuffer(" from BookContentEntity entity ");
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
            hql.append("and entity.title like :search");
            params.put("search", "%" + search + "%");
        }
        String queryHql = hql.toString();

        if (orderBy.length() > 0) {
            queryHql += " order by " + orderBy + " desc ";
        }

        Page resultPage = baseDao.pagedQuery(queryHql, pageNo, pageSize, params);
        List<BookContentEntity> list = (List<BookContentEntity>) resultPage.getResult();
        resultPage.setResult(DaoUtil.convertDataList(list));
        return resultPage;
    }

    @Transient
    public Map<String, Object> getWhereParam(BookContentPo t) {
        Map<String, Object> params = new HashMap<String, Object>();

        return params;

    }

    @Transient
    public String getWhereSql(BookContentPo t) {
        StringBuffer sb = new StringBuffer("where 1=1");
        return sb.toString();
    }
}
