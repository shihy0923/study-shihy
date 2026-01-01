package com.example.protostuff;

/**
 * Note:
 * Date: 2025/7/22 23:30
 * Author: shihy
 */
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Arrays;
import java.util.List;

public class ProtoStuffCorrectDemo {
    public static void main(String[] args) {
        // 准备数据
        List<User> userList = Arrays.asList(
                new User("Alice", 25),
                new User("Bob", 30)
        );
        UserListWrapper wrapper = new UserListWrapper(userList);

        // 获取 schema
        Schema<UserListWrapper> schema = RuntimeSchema.getSchema(UserListWrapper.class);

        // 序列化
        LinkedBuffer buffer = LinkedBuffer.allocate(256);
        byte[] bytes = ProtostuffIOUtil.toByteArray(wrapper, schema, buffer);

        // 反序列化
        UserListWrapper newWrapper = new UserListWrapper();
        ProtostuffIOUtil.mergeFrom(bytes, newWrapper, schema);

        System.out.println("反序列化后的数据: " + newWrapper.getUsers());
    }
}

