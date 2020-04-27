package com.cl.interview.dao;

import com.cl.interview.entity.BookContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookContentDao extends JpaRepository<BookContentEntity, String> {
}
