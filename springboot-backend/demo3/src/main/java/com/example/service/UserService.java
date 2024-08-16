package com.example.service;

import com.example.pojo.User;

import java.util.Map;

public interface UserService {
    User findByName(String username);
    void register(String username,String password);

    void update(User user);

    void updateAvatar(String url);

    void updatePassword(String md5, int id);
}
