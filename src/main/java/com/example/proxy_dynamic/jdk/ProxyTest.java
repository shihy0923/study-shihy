package com.example.proxy_dynamic.jdk;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: ProxyTest.java
 * @Description:
 * @Version: v1.0.0
 * @author: shihy
 * @date 2019年09月30日
 */
public class ProxyTest {


    public static void main(String[] args) throws IOException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        SupMan supMan = new SupMan();
        supMan.getBelief();
        Human proxyInstance = (Human) getProxyInstance(supMan);
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("臭豆腐","我喜欢吃");
    }

    public static Object getProxyInstance(Object object) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.setObject(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),myInvocationHandler);
    }
}
/*
* 一个调用处理器
* */
class MyInvocationHandler implements InvocationHandler {
    //目标对象
    private Object object;
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }
}

class SupMan implements Human {
    @Override
    public void eat(String food, String like) {
        System.out.println(like+food);
    }
    @Override
    public String getBelief() {
        this.getClass().getClassLoader().getResourceAsStream("");
        return "我是最帅的！";
    }
}

interface Human {
    public void eat(String food, String like);

    public String getBelief();
}


