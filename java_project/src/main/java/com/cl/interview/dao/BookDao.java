package com.cl.interview.dao;

import com.cl.interview.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<BookEntity, String> {
}
