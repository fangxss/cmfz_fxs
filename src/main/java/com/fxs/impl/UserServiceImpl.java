package com.fxs.impl;

import com.fxs.dao.UserDao;
import com.fxs.entity.User;
import com.fxs.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Override
    public HashMap<String,Object> selectBypage(Integer page, Integer rows) {
        System.out.println(page);
        Integer pages=(page-1)*rows;
        List<User> users = userDao.selectBypage(pages, rows);
        System.out.println(users.size());
        Integer count = userDao.selectCount();
        HashMap<String, Object> map = new HashMap<>();
        Integer total=count % rows==0 ? count/rows :count /rows +1;
        map.put("records",count);
        map.put("total",total);
        map.put("page",page);
        map.put("rows",users);
        return map;
    }

    @Override
    public void updateUser(User user) {

        userDao.updatUser(user);
    }
}
