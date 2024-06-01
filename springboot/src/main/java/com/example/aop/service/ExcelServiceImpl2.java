package com.example.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @ClassName: ExcelServiceImpl2.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2022年02月12日
 */
@Component
@Slf4j
public class ExcelServiceImpl2 {

//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Autowired ExcelServiceImpl excelService;
    public void excelImport2()  {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("ddddddddd");
    }
}
