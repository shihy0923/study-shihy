package com.example.innerclass.innerClass;

/**
 * @ClassName: OuterClass.java
 * @Description: 内部类
 * @Version: v1.0.0
 * @author: shihy
 * @date 2021年03月24日
 */
class OuterClass {
    int x = 10;

    class InnerClass {
        public final static String string = "2";
        public int myInnerMethod() {
            return x;
        }
    }
    public  int write() {
        return new InnerClass().myInnerMethod();
    }
}

public class MyMainClass {
    public static void main(String[] args) {
        OuterClass myOuter = new OuterClass();
        OuterClass.InnerClass myInner = myOuter.new InnerClass();
        System.out.println(myInner.myInnerMethod());
    }
}