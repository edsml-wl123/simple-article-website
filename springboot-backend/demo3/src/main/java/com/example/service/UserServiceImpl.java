package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.utils.ThreadLocalUtil;
import com.example.utils.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByName(String username) {
        User user=userMapper.findByName(username);
        return user;
    }

    @Override
    public void register(String username,String password) {
        String encrypted_pw= md5.MD5(password);

        userMapper.add(username,encrypted_pw);

    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String url) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updateAvatar(url,id);
    }

    @Override
    public void updatePassword(String md5, int id) {
        userMapper.updatePassword(md5,id);
    }
}
