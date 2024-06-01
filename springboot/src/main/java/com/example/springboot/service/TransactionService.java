package com.example.springboot.service;

import com.example.springboot.entity.Department;

/**
 * Note: 事务接口
 * Date: 2023/7/6 18:29
 * Author: shihy
 */
public interface TransactionService {

   default int update1(Department department){
       return 0;
   }
   default   int update2(){
       return 0;
   }
}
