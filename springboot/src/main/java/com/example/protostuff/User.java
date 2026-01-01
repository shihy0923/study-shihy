package com.example.protostuff;

/**
 * Note:
 * Date: 2025/7/22 23:29
 * Author: shihy
 */
public class User {
    private String name;
    private int age;

    public User() {} // 必须有无参构造

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter / Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}

