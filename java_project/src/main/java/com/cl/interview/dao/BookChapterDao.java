package com.cl.interview.dao;

import com.cl.interview.entity.BookChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookChapterDao extends JpaRepository<BookChapterEntity, String> {

    List<BookChapterEntity> findByParentId(String parentId);
    List<BookChapterEntity> findByLevel(Integer level);
    List<BookChapterEntity> findByBookIdAndLevel(String bookId,Integer level);
}
