package com.example.springboot.service.impl;

import com.example.springboot.entity.Department;
import com.example.springboot.mapper.DepartmentMapper;
import com.example.springboot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Note:
 * Date: 2023/7/6 18:31
 * Author: shihy
 */
@Service
@Slf4j
public class TransactionServiceImpl1 implements TransactionService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Resource(name = "transactionServiceImpl2")
    TransactionService transactionService;

    @Override
    @Transactional
    public int update1(Department department) {
        log.info("执行事务1");

        transactionService.update2();

        return departmentMapper.update(department);
    }
}
