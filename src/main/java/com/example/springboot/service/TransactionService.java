package com.example.springboot.service;

import com.example.springboot.entity.Department;

/**
 * Note: 事务接口
 * Date: 2023/7/6 18:29
 * Author: shihy
 */
public interface TransactionService {

    int update(Department department);
}
