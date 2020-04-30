package com.cl.interview.service.impl;

import com.cl.interview.common.HttpResp;
import com.cl.interview.common.Page;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.dao.PracticeDao;
import com.cl.interview.entity.PracticeEntity;
import com.cl.interview.entity.QuestionEntity;
import com.cl.interview.po.PracticePo;
import com.cl.interview.service.PracticeService;
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
@Service("practiceService")
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    PracticeDao dao;

    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    @Override
    public List<PracticePo> findAll() {
        return DaoUtil.convertDataList(dao.findAll());
    }

    @Override
    public PracticePo save(PracticePo obj) {
        obj.setUpdateTime(Calendar.getInstance().getTime());
        if (StringUtils.isEmpty(obj.getScoreRecord())) {
            obj.setScoreRecord(String.valueOf(obj.getScore()));
        } else {
            obj.setScoreRecord(obj.getScoreRecord() + "_" + String.valueOf(obj.getScore()));

        }
        return dao.save(obj.toObject()).toObject();
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public PracticePo getOne(String id) {
        PracticeEntity entity = dao.getOne(id);
        if (entity != null) {
            return entity.toObject();
        }
        return null;
    }

    @Override
    public void delete(Iterable<PracticePo> entities) {
        if (entities != null) {
            for (PracticePo po : entities) {
                dao.delete(po.toObject());
            }
        }
    }


    @Override
    public Page getData(int pageNo, int pageSize, String cateId, Integer score, String userId) {
        StringBuffer hql = new StringBuffer(" from QuestionEntity Q LEFT JOIN PracticeEntity P ON P.questionId = Q.id ");
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(cateId)) {
            hql.append(" and Q.cateId = :cateId ");
            params.put("cateId", cateId);
        }
        if (!StringUtils.isEmpty(userId)) {
            hql.append(" and Q.userId = :userId ");
            params.put("userId", userId);
        }
        if (score != null && score > 0) {
            hql.append(" and P.score = :score ");
            params.put("score", score);
        } else {

            hql.append(" and P.id is null ");
        }

        Page resultPage = baseDao.pagedQuery(hql.toString(), pageNo, pageSize, params);
        List result = (List) resultPage.getResult();
        resultPage.setResult(result);
        return resultPage;
    }

    @Override
    public Page getDataByPage(int pageNo, int pageSize, PracticePo obj, List<String> orderBysList, String search) {
        StringBuffer hql = new StringBuffer(" from PracticeEntity entity ");
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

        String queryHql = hql.toString();

        if (orderBy.length() > 0) {
            queryHql += " order by " + orderBy + " desc ";
        } else {
            queryHql += " order by noteNum desc ";
        }

        Page resultPage = baseDao.pagedQuery(queryHql, pageNo, pageSize, params);
        List<PracticeEntity> list = (List<PracticeEntity>) resultPage.getResult();
        resultPage.setResult(DaoUtil.convertDataList(list));
        return resultPage;
    }

    @Override
    public String doSerializable() {
        String fileName = this.getClass().getSimpleName();
        String filePath = config.getWebFilePath();
        List<PracticePo> list = this.findAll();
        try {
            SerializableFile.doSerializable(list, filePath + File.separator + fileName + ".out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HttpResp saveByParams(List params, String userId) {
        for (int i = 0; i < params.size(); i++) {
            Map map = (Map) params.get(i);
            PracticePo po = new PracticePo();
            po.setId((String) map.get("practiceId"));
            po.setQuestionId((String) map.get("questionId"));
            po.setScore((Integer) map.get("score"));
            createOrUpdate(po);
        }
        return null;
    }

    @Override
    public void createOrUpdate(PracticePo po) {
        // TODO Auto-generated method stub
        PracticePo old = getOne(po.getId());
        if (old == null) {
            save(po);
        } else {
            save((PracticePo) ClassUtils.inheritValue(po, po, new HashSet<String>() {
                {
                    add("scoreRecord");
                    add("updateTime");
                }
            }));

        }
    }


    @Override
    public void saveByFile(MultipartFile mf) {
        if (mf != null) {
            List<PracticePo> list = null;
            try {
                list = (List<PracticePo>) SerializableFile.upSerializable(mf.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (list != null) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    PracticePo po = list.get(i);
                    boolean add = true;
                    if (po.getId() != null) {
                        PracticePo old = getOne(po.getId());
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


    @Transient
    public Map<String, Object> getWhereParam(PracticePo t) {
        Map<String, Object> params = new HashMap<String, Object>();

        return params;

    }

    @Transient
    public String getWhereSql(PracticePo t) {
        StringBuffer sb = new StringBuffer("where 1=1");
        return sb.toString();
    }
}
