package com.example.innerclass.anonymity;

/**
 * @ClassName: OuterClass.java
 * @Description: 匿名内部类
 * @Version: v1.0.0
 * @author: shihy
 * @date 2021年03月24日
 */


public class MyMainClass {
    public static void main(String[] args) {
        MyMainClass myMainClass = new MyMainClass();
        System.out.println(myMainClass.myWrite());
    }

    public String myWrite() {
        Anonymity anonymity = new Anonymity() {
            @Override
            public String write() {
                return "1";
            }
        };
        return anonymity.write();
    }
}
