package com.cl.interview.dao;

import com.cl.interview.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BookDao extends JpaRepository<BookEntity, String> {
    @Transactional
    @Modifying
    @Query("update BookEntity set noteNum = noteNum +1 where id = :id")
    Integer addNoteNum(@Param(value = "id") String id);

}
