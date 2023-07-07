package com.example.aop;

import com.example.aop.service.ExcelServiceImpl;
import com.example.aop.service.targetSource.Apple;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: TestController.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2022年02月13日
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ExcelServiceImpl excelService;

    @Resource
    Apple apple;

    @PostMapping("/testAop")
    public void testAop() {
        excelService.unAopExcelImport("dddddd");
        excelService.excelImport("dddddd");
    }

    @PostMapping("/testTargetSource")
    public void testTargetSource() throws Exception {
        for (int i = 0; i < 10; i++) {
            apple.eat();
        }
    }
}
