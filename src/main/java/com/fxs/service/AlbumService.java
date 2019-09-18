package com.fxs.service;


import com.fxs.entity.Album;

import java.util.HashMap;

public interface AlbumService {
    //查询所有数据
    public HashMap<String,Object> selectAll(Integer page, Integer rows);

    //添加数据
    public  String addAlbum(Album album);

    //修改数据
    public  HashMap<String,Object> updateAlbum(Album album);

    //删除数据
    public void deleteAlbum(String id);

}
