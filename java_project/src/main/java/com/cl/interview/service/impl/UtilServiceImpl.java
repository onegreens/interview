package com.cl.interview.service.impl;

import com.alibaba.fastjson.JSON;
import com.cl.interview.common.HttpResp;
import com.cl.interview.config.BaseConfig;
import com.cl.interview.dao.BaseDao;
import com.cl.interview.exception.ArgumentException;
import com.cl.interview.po.BookPo;
import com.cl.interview.service.UtilService;
import com.cl.interview.util.MDDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Transactional
@Service("utilService")
public class UtilServiceImpl implements UtilService {
    private static final Map<String, String> tableNames = new HashMap<>();

    static {
        tableNames.put("book", "BookEntity");
        tableNames.put("bookChapter", "BookChapterEntity");
        tableNames.put("bookContent", "BookContentEntity");
        tableNames.put("question", "QuestionEntity");
    }


    @Autowired
    BaseConfig config;

    @Resource
    private BaseDao baseDao;

    @Resource
    private EntityManager entityManager;

    @Override
    public HttpResp doJson(String tableAlias) throws ArgumentException {
        String tableName = tableNames.get(tableAlias);
        if (tableName.isEmpty()) {
            throw new ArgumentException("不存在该参数");
        }

        HttpResp resp = new HttpResp();
        StringBuilder sql = new StringBuilder();
        sql.append("from ").append(tableName);
        Query query=entityManager.createQuery(sql.toString());
        List list = query.getResultList();

        StringBuffer path = new StringBuffer(config.getWebFilePath());
        path.append(File.separator).append(tableAlias).append(System.currentTimeMillis()).append(".json");
        MDDataUtil.outFile(path.toString(), JSON.toJSON(list).toString());
        return resp;
    }

    @Override
    public String saveImage(MultipartFile mf) {
        String fileName = mf.getOriginalFilename();
        String suffixName =fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String parent = config.getWebImagePath()+File.separator;
        String imgName = "";
        try {
            File targetFile = new File(parent, fileName);
            // 创建文件夹
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 将上传文件存储到服务器中
            mf.transferTo(targetFile);
            // 背景图片地址
            imgName = targetFile.getName();
            imgName = config.getWebImageUrl() + imgName;
            System.out.println(imgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgName;
    }
}
