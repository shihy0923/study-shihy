package com.example.protostuff;

// ProtoStuffDebugDemo.java
// ------------------------------------------------------------
// 一个简单的 ProtoStuff 字节级调试工具示例。
// 运行后会：
// 1. 将 UserListWrapper 对象序列化为字节并打印十六进制
// 2. 使用 ByteDebugger 解析字节，输出字段号 (fieldNumber)、wireType 和原始值/长度
//
// 依赖（Maven）：
// <dependency>
//     <groupId>io.protostuff</groupId>
//     <artifactId>protostuff-core</artifactId>
//     <version>1.8.0</version>
// </dependency>
// <dependency>
//     <groupId>io.protostuff</groupId>
//     <artifactId>protostuff-runtime</artifactId>
//     <version>1.8.0</version>
// </dependency>
// ------------------------------------------------------------

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProtoStuffDebugDemo {

    public static void main(String[] args) throws IOException {
        // 1️⃣ 准备示例数据
        List<User> users = new ArrayList<>();
        users.add(new User("Tom", 20));
        users.add(new User("Jerry", 22));
        UserListWrapper wrapper = new UserListWrapper(users);

        // 2️⃣ 获取 schema & 序列化
        Schema<UserListWrapper> schema = RuntimeSchema.getSchema(UserListWrapper.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(wrapper, schema, LinkedBuffer.allocate(256));

        // 3️⃣ 打印十六进制
        System.out.println("Hex dump:");
        System.out.println(ByteDebugger.bytesToHex(bytes));

        // 4️⃣ 调用调试器解析字段
        System.out.println("\n--- Parsed Fields ---");
        ByteDebugger.parse(bytes);
    }

    // --------------------------------------------------------
    // POJO: User
    // --------------------------------------------------------
    public static class User {
        private String name;
        private int age;

        public User() {}
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    // --------------------------------------------------------
    // POJO: UserListWrapper (封装 List<User>)
    // --------------------------------------------------------
    public static class UserListWrapper {
        private List<User> users;

        public UserListWrapper() {}
        public UserListWrapper(List<User> users) { this.users = users; }
        public List<User> getUsers() { return users; }
        public void setUsers(List<User> users) { this.users = users; }
    }

    // --------------------------------------------------------
    // Debugger: 解析 ProtoStuff/Protobuf 字节，输出 tag 详情
    // --------------------------------------------------------
    public static class ByteDebugger {
        /**
         * 将字节数组转换为十六进制可读字符串
         */
        public static String bytesToHex(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X ", b));
            }
            return sb.toString();
        }

        /**
         * 解析字节，打印 fieldNumber, wireType, 以及 varint 或 length-delimited 的原始值/长度
         */
        public static void parse(byte[] data) throws IOException {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            while (true) {
                int tag = readVarInt32(in);
                if (tag == -1) break; // EOF
                int fieldNumber = tag >>> 3;
                int wireType = tag & 0x07;
                System.out.print("FieldNumber=" + fieldNumber + ", WireType=" + wireType);
                switch (wireType) {
                    case 0: // varint
                        long varintVal = readVarInt64(in);
                        System.out.println(", VarInt=" + varintVal);
                        break;
                    case 2: // length‑delimited
                        int length = readVarInt32(in);
                        byte[] value = new byte[length];
                        if (in.read(value) != length) throw new IOException("Unexpected EOF");
                        System.out.println(", Length=" + length + ", ValueBytes=[" + bytesToHex(value) + "]");
                        break;
                    default:
                        System.out.println(" (wire type not handled, raw bytes remaining)");
                        return;
                }
            }
        }

        // ------------------ 辅助：读取 varint ------------------
        private static int readVarInt32(ByteArrayInputStream in) throws IOException {
            int result = 0;
            int shift = 0;
            int b;
            while (shift < 32) {
                b = in.read();
                if (b == -1) return -1; // EOF
                result |= (b & 0x7F) << shift;
                if ((b & 0x80) == 0) {
                    return result;
                }
                shift += 7;
            }
            throw new IOException("Malformed varint32");
        }

        private static long readVarInt64(ByteArrayInputStream in) throws IOException {
            long result = 0;
            int shift = 0;
            int b;
            while (shift < 64) {
                b = in.read();
                if (b == -1) throw new IOException("EOF while reading varint64");
                result |= (long) (b & 0x7F) << shift;
                if ((b & 0x80) == 0) {
                    return result;
                }
                shift += 7;
            }
            throw new IOException("Malformed varint64");
        }
    }
}

