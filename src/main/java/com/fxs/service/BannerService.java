package com.fxs.service;


import com.fxs.entity.Banner;

import java.util.HashMap;

public interface BannerService {

    //查询所有数据
    public HashMap<String,Object> selectAll(Integer page, Integer rows);

    //添加数据
    public  String addBanner(Banner banner);

    //修改数据
    public  HashMap<String,Object> updateBanner(Banner banner);

    //删除数据
    public void deleteBanner(String id);
}
