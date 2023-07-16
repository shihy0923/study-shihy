package com.example.aop.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MainWorkService.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2020年12月02日
 */
//@Aspect
@Service
@Slf4j
public class AspectService1 {

    @Pointcut("execution(* com.example.aop.service.ExcelServiceImpl.excelImport(String))")
    public void pointCut1() {
    }


    @Before(value = "pointCut1()")
    public void checkTokenBefore(JoinPoint joinPoint) {
        joinPoint.getArgs();
        System.out.println("before执行");
    }

    @After(value = "pointCut1()")
    public void checkTokenAfter(JoinPoint joinPoint) {
        System.out.println("After执行");
    }

    public void checkTokenAACommon(JoinPoint joinPoint) {
        System.out.println("After执行");
    }

}
