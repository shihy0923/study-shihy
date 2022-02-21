package com.example.proxy_dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: MyMethodInterceptor.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2020年02月26日
 */
public class MyMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        methodProxy.invokeSuper(o, objects);
        System.out.println("======插入后者通知======");
        return null;
    }
}
