package com.cl.interview.service.impl;

import com.alibaba.fastjson.JSON;
import com.cl.interview.common.HttpResp;
import com.cl.interview.common.IoTErrorCode;
import com.cl.interview.common.MDData;
import com.cl.interview.common.Page;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.dao.BookDao;
import com.cl.interview.dto.BookDto;
import com.cl.interview.entity.BookEntity;
import com.cl.interview.po.BookPo;
import com.cl.interview.service.BookChapterService;
import com.cl.interview.service.BookService;
import com.cl.interview.util.ClassUtils;
import com.cl.interview.util.DaoUtil;
import com.cl.interview.util.MDDataUtil;
import com.cl.interview.util.SerializableFile;
import com.fasterxml.jackson.core.JsonGenerator;
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
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao dao;
    @Autowired
    BookChapterService chapterService;

    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    @Override
    public List<BookPo> findAll() {
        return DaoUtil.convertDataList(dao.findAll());
    }

    @Override
    public BookPo save(BookPo obj) {
        obj.setCreateTime(Calendar.getInstance().getTime());
        return dao.save(obj.toObject()).toObject();
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public BookPo getOne(String id) {
        BookEntity entity = dao.getOne(id);
        if (entity != null) {
            return entity.toObject();
        }
        return null;
    }

    @Override
    public void delete(Iterable<BookPo> entities) {
        if (entities != null) {
            for (BookPo po : entities) {
                dao.delete(po.toObject());
            }
        }
    }

    @Override
    public HttpResp create(BookDto dto) {
        HttpResp resp = new HttpResp();
        save(dto.toObject());
        return resp;
    }

    @Override
    public HttpResp update(BookDto dto) {
        // TODO Auto-generated method stub
        HttpResp resp = new HttpResp();

        BookPo po = getOne(dto.getId());
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("编辑习题信息失败，习题信息不存在");
            return resp;
        }
        save((BookPo) ClassUtils.inheritValue(po, dto, new HashSet<String>() {
            {
                add("noteNum");
                add("createTime");
                add("userId");
            }
        }));

        return resp;
    }

    @Override
    public String doSerializable() {
        String fileName = this.getClass().getSimpleName();
        String filePath = config.getWebFilePath();
        List<BookPo> list = this.findAll();
        try {
            SerializableFile.doSerializable(list, filePath + File.separator + fileName + ".out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MDData toMD(BookPo po) {
        MDData data = new MDData(po.getName(), 0);
        List<MDData> result = chapterService.toMD(po.getId());
        data.setList(result);
        return data;
    }

    @Override
    public HttpResp toMDFile(String bookId) {
        HttpResp resp = new HttpResp();
        BookPo po = getOne(bookId);
        if (po == null) {
            resp.setCode(IoTErrorCode.ITEM_NOT_FOUND.getErrorCode());
            resp.setMessage("导出失败，习题信息不存在");
        }
        String filePath = config.getWebFilePath() + File.separator + po.getName() + ".md";
        MDDataUtil.outFile(filePath, toMD(po));
        return resp;
    }

    @Override
    public void saveByFile(MultipartFile mf) {
        if (mf != null) {
            List<BookPo> list = null;
            try {
                list = (List<BookPo>) SerializableFile.upSerializable(mf.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (list != null) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    BookPo po = list.get(i);
                    boolean add = true;
                    if (po.getId() != null) {
                        BookPo old = getOne(po.getId());
                        if (old != null) {
                            add = false;
                        }
                    }
                    if (add) {
                        save(po);
                    }
                }
            }
        }
    }

    @Override
    public Page getDataByPage(int pageNo, int pageSize, BookPo obj, List<String> orderBysList, String search) {

        StringBuffer hql = new StringBuffer(" from BookEntity entity ");
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
        }else{
            queryHql += " order by noteNum desc ";
        }

        Page resultPage = baseDao.pagedQuery(queryHql, pageNo, pageSize, params);
        List<BookEntity> list = (List<BookEntity>) resultPage.getResult();
        resultPage.setResult(DaoUtil.convertDataList(list));
        return resultPage;
    }

    @Transient
    public Map<String, Object> getWhereParam(BookPo t) {
        Map<String, Object> params = new HashMap<String, Object>();

        return params;

    }

    @Transient
    public String getWhereSql(BookPo t) {
        StringBuffer sb = new StringBuffer("where 1=1 ");
        return sb.toString();
    }
}
