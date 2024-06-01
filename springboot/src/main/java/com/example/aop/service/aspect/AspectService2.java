package com.example.aop.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MainWorkService2.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2022年02月13日
 */
//@Aspect
@Service
@Slf4j
public class AspectService2 {

    @Pointcut("execution(* com.example.aop.service.ExcelServiceImpl.excelImport(..))")
    public void pointCut() {
    }


    @Before(value = "pointCut()")
    public void checkTokenBefore(JoinPoint joinPoint) {
        System.out.println("before2执行");
    }

    @After(value = "pointCut()")
    public void checkTokenAfter(JoinPoint joinPoint) {
        System.out.println("After2执行");
    }

}
