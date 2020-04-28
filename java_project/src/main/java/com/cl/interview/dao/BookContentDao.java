package com.cl.interview.dao;

import com.cl.interview.entity.BookChapterEntity;
import com.cl.interview.entity.BookContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookContentDao extends JpaRepository<BookContentEntity, String> {
    List<BookContentEntity> findByChapterId(String bookId);

}
