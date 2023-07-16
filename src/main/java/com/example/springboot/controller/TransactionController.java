package com.example.springboot.controller;

import com.example.springboot.entity.Department;
import com.example.springboot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Note:
 * Date: 2023/7/6 18:34
 * Author: shihy
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    @PostMapping("/update")
    public Map update(@RequestBody  Department department) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("result", transactionService.update(department));
        return objectObjectHashMap;
    }

    public Map update2(@RequestBody  Department department) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("result", transactionService.update(department));
        return objectObjectHashMap;
    }
}
