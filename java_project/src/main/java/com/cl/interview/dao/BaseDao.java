package com.cl.interview.dao;


import com.cl.interview.common.Page;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.TupleSubsetResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Dao查询基类，包括分页，sql
 *
 * @author wengzhonghui
 */
@Slf4j
@Repository
public class BaseDao {
    @Resource
    private EntityManager entityManager;

    /**
     * 根据SQL查询List列表
     *
     * @param sql
     * @param map
     * @return
     */
    public List queryBySql(String sql, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.getResultList();
    }

    /**
     * 根据SQL查询List列表
     *
     * @param sql
     * @param map
     * @return
     */
    public List queryBySql(String sql, Map<String, Object> map, TupleSubsetResultTransformer former) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(former);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.getResultList();
    }

    /**
     * 根据SQL查询List列表
     *
     * @param sql
     * @param map
     * @return
     */
    public List queryBySql(String sql, Map<String, Object> map, Object ob) {
        Query query = entityManager.createNativeQuery(sql, ob.getClass());
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.getResultList();
    }

    /**
     * 根据hql查询List列表
     *
     * @param hql
     * @param map
     * @return
     */
    public List queryByHql(String hql, Map<String, Object> map) {
        Query query = entityManager.createQuery(hql);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.getResultList();
    }

    /**
     * hql的分页查询
     *
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param map
     * @return
     */
    public Page pagedQuery(String hql, int pageNo, int pageSize, Map<String, Object> map) {
        Query query = entityManager.createQuery(hql);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        int start = (pageNo - 1) * pageSize;
        List result = query.setFirstResult(start).setMaxResults(pageSize).getResultList();

        // Count查询
        String countQueryString = "select count (*) "
                + removeSelect(removeOrders(hql));
        Integer totalCount = this.getCount(countQueryString, map);

        if (totalCount < 1) {
            return new Page();
        }


        Page page = new Page(result, totalCount);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        return page;
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     *
     * @param hql HQL字符串
     * @return 删除select语句后的字符串
     */
    public static String removeSelect(String hql) {
        Assert.hasText(hql);

        if (hql.toLowerCase(Locale.ENGLISH).indexOf("distinct") != -1) {
            log.warn(
                    "there is a distinct in paged query hql : [{}], this maybe cause an unexpected result",
                    hql);
        }

        int beginPos = hql.toLowerCase(Locale.CHINA).indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql
                + " must has a keyword 'from'");

        return hql.substring(beginPos);
    }

    /**
     * 去除hql的order by 子句，用于pagedQuery.
     *
     * @param hql HQL字符串
     * @return 删除排序语句后的字符串
     */
    public static String removeOrders(String hql) {
        Assert.hasText(hql);

        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);

        return sb.toString();
    }

    /**
     * 获得总记录数.
     *
     * @param hql HQL字符串
     * @param map Map
     * @return 总数
     */
    public Integer getCount(String hql, Map<String, Object> map) {

        try {
            Query query = entityManager.createQuery(hql);
            if (map != null) {
                for (Serializable key : map.keySet()) {
                    query.setParameter((String) key, map.get(key));
                }
            }
            Object result = query.getSingleResult();
            return getNumber(result);
        } catch (Exception e) {
//			logger.error(e.getMessage(),e);
        }
        return null;
    }


    /**
     * get number for count.
     *
     * @param result Object
     * @return Integer
     */
    public static Integer getNumber(Object result) {
        if (result == null) {
            return 0;
        } else {
            return ((Number) result).intValue();
        }
    }

    @Transactional
    public int updateByHql(String hql, Map<String, Object> map) {
        Query query = entityManager.createQuery(hql);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.executeUpdate();
    }

    @Transactional
    public int updateBySql(String sql, Map<String, Object> map) {
        Query query = entityManager.createNativeQuery(sql);
        for (Serializable key : map.keySet()) {
            query.setParameter((String) key, map.get(key));
        }
        return query.executeUpdate();
    }
}
