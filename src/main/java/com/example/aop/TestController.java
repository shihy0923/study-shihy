package com.example.aop;

import com.example.aop.service.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/test")
    public void test() {
        excelService.unAopExcelImport("dddddd");
        excelService.excelImport("dddddd");
    }
}
