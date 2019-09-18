package com.fxs.service;

import com.fxs.entity.User;

import java.util.HashMap;

public interface UserService {

    //分页查询
    public HashMap<String,Object> selectBypage(Integer page, Integer rows);

    //修改
    public void updateUser(User user);



}
