package com.fxs.dao;

import com.fxs.entity.Album;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AlbumDao {
    //分页查询、
    public List<Album> selectBypage(@Param("start") Integer start,@Param("rows")Integer rows);

    //增加专辑
    public void insertAlbum(Album album);

    //修改专辑信息
    public void updateAlbum(Album album);

    //删除专辑信息
    public void deleteAlbum(String id);

    //查询总条数
    public Integer  selectCount();

}
