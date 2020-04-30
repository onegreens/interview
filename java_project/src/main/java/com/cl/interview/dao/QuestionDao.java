package com.cl.interview.dao;

import com.cl.interview.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<QuestionEntity, String> {
}
