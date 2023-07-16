package com.example.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

/**
 * @ClassName: ExcelServiceImpl.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2021年06月16日
 */

@Component
@Slf4j
public class ExcelServiceImpl {

    @Autowired
    ExcelServiceImpl2 excelServiceImpl2;
//    @Transactional
@Async
    public Map<String, Object> excelImport(String str)  {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        excelServiceImpl2.excelImport2();
        return null;

    }


    public Map<String, Object> unAopExcelImport(String str)  {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        excelServiceImpl2.excelImport2();
        return null;

    }


}

