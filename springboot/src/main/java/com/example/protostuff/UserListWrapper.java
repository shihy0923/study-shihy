package com.example.protostuff;

import java.util.List;

/**
 * Note:
 * Date: 2025/7/22 23:30
 * Author: shihy
 */
public class UserListWrapper {
    private List<User> users;

    public UserListWrapper() {}

    public UserListWrapper(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}

