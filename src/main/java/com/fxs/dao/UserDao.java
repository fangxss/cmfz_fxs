package com.fxs.dao;


import com.fxs.entity.User;

import java.util.List;

public interface UserDao {

    //分页查询
    public List<User> selectBypage(Integer start,Integer rows);

    //修改
    public void updatUser(User user);

    //查询总条数
    public Integer  selectCount();

}
