package com.example.springboot.service.impl;

import com.example.springboot.entity.Department;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Note:
 * Date: 2023/7/6 18:31
 * Author: shihy
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public int update(Department department) {

//        Department department = new Department();
//
//        department.setId("18ec781fbefd727923b0d35740b177ab");
//        department.setTel("444444");
//        department.setName("开发部2");
        return departmentMapper.update(department);
    }
}
