package com.example.aop;

import cn.hutool.core.util.ReUtil;
import com.example.aop.service.ExcelServiceImpl;
import com.example.aop.service.targetSource.Apple;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
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



    public static void main(String[] args) {
        ReUtil.isMatch("^/brand/[\\w]+(-[\\w]+)*(-\\d+)?$", "/brand/adidas-shoes");

        // 测试用例
        String[] urls = {
                "nike",
                "new-balance-4",
                "361-11212",
                "nike-144",
                "424-100u1834",
                "s-max-mara-1001305",
                "kent-curwen-1008629",
                "0-croworld-1012440",
                "1017-alyx-9sm-1000301",
                "medicos-entertainment-co-ltd-1000842"
        };

        for (String url : urls) {
            System.out.println("Original: " + url + " -> Processed: " + processUrlSegment(url));
        }
    }

    public static String processUrlSegment(String urlSegment) {
        // Remove the last part after the last "-" which is assumed to be a numeric ID
        int lastDashIndex = urlSegment.lastIndexOf("-");
        if (lastDashIndex != -1 && isAllDigits(urlSegment.substring(lastDashIndex + 1))) {
            urlSegment = urlSegment.substring(0, lastDashIndex);
        }

        // Replace all "-" with spaces
        urlSegment = urlSegment.replace("-", " ");

        // Capitalize each word
        String[] words = urlSegment.split(" ");
        StringBuilder processed = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                if (processed.length() > 0) processed.append(" ");
                processed.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
            }
        }

        return processed.toString();
    }

    private static boolean isAllDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
