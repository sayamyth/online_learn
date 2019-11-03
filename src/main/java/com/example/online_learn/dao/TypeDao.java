package com.example.online_learn.dao;

import com.example.online_learn.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeDao extends JpaRepository<Type,Long>, JpaSpecificationExecutor<Type> {
    Type findByTypeName(String typename);
}
