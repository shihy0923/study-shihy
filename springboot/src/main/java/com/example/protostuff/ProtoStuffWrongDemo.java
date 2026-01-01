package com.example.protostuff;

/**
 * Note:
 * Date: 2025/7/22 23:33
 * Author: shihy
 */
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;

public class ProtoStuffWrongDemo {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Tom", 20));
        userList.add(new User("Jerry", 22));

        // ❌ 错误用法：把 ArrayList 当成 POJO 获取 schema
        Schema schema = RuntimeSchema.getSchema(ArrayList.class); // 不会知道里面的 User 类型
        byte[] bytes = ProtostuffIOUtil.toByteArray((ArrayList) userList, schema, LinkedBuffer.allocate(256));

        // 同样错误的反序列化方式
        ArrayList result = new ArrayList();
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);

        System.out.println("反序列化后的数据（结构未知）: " + result);
    }
}

