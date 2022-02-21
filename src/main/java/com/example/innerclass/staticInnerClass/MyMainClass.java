package com.example.innerclass.staticInnerClass;

/**
 * @ClassName: OuterClass.java
 * @Description: 静态内部类
 * @Version: v1.0.0
 * @author: shihy
 * @date 2021年03月24日
 */
class OuterClass {
    int x = 10;

    static class InnerClass {
        int y = 5;

    }

    public  int write() {
        return new InnerClass().y;
    }
}

public class MyMainClass {
    public static void main(String[] args) {
        OuterClass.InnerClass myInner = new OuterClass.InnerClass();
        System.out.println(myInner.y);
    }
}
