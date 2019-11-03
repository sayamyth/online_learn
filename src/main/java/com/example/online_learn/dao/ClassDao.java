package com.example.online_learn.dao;

import com.example.online_learn.entity.Klass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassDao extends JpaRepository<Klass,Long>, JpaSpecificationExecutor<Klass> {
    Klass findByClassName(String classname);
}
