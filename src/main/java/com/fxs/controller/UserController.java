package com.fxs.controller;

import com.fxs.entity.User;
import com.fxs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("showBypage")
    public HashMap<String,Object> showBypage(Integer page,Integer rows)throws Exception{
        System.out.println("1111111111");
        HashMap<String, Object> map = userService.selectBypage(page, rows);
        System.out.println("222222222");
        return map;
    }

    @RequestMapping("edit")
    public String edit(User user,String oper)throws Exception{
        if(oper.equals("edit")){
            userService.updateUser(user);
        }
        return "成功";
    }

    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(String id, String status)throws Exception{
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userService.updateUser(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message","成功");
        return map;
    }
}
