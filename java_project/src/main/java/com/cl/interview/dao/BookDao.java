package com.cl.interview.dao;

import com.cl.interview.entity.BookEntity;
import com.cl.interview.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findByParentId(Integer parentId);
}
