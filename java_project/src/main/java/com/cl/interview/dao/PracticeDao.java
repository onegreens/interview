package com.cl.interview.dao;

import com.cl.interview.entity.PracticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeDao extends JpaRepository<PracticeEntity, String> {

}
