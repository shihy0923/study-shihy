package com.example.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Note: OOMTest
 * Date: 2023/11/25 17:50
 * Author: shihy
 */
public class OOMTest {

    public static List<Object> list = new ArrayList<>();

    // JVM设置    
    // -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./jvm.dump
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }
}
