package com.fxs.dao;

import com.fxs.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminDao {

    //查询用户
    public Admin select(String username);

}
