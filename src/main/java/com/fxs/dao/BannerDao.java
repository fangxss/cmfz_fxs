package com.fxs.dao;

import com.fxs.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BannerDao {
    //查询所有信息
    public List<Banner> selectAll(@Param("start") Integer start,@Param("rows") Integer rows);

    //增加轮播图
    public void insertBanner(Banner banner);

    //修改轮播图信息
    public void updateBanner(Banner banner);

    //删除轮播图信息
    public void deleteBanner(String id);

    //查询总条数
    public Integer  selectCount();
}
