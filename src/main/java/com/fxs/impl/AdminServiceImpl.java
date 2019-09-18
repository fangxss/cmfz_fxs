package com.fxs.impl;

import com.fxs.dao.AdminDao;
import com.fxs.entity.Admin;
import com.fxs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public void login(Admin admin) {
        Admin admin1 = adminDao.select(admin.getUsername());
        if(admin1==null||"".equals(admin1))throw new RuntimeException("用户名不存在");
        if(!admin.getPassword().equals(admin1.getPassword()))throw new RuntimeException("密码错误");
    }
}
