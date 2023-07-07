package com.example.transaction.controller;

import com.example.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Note:
 * Date: 2023/7/6 18:34
 * Author: shihy
 */
@RestController
@Slf4j
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    @PostMapping("/update")
    public int update() {
        return transactionService.update();
    }
}
