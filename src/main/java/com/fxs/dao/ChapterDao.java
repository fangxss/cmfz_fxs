package com.fxs.dao;

import com.fxs.entity.Album;
import com.fxs.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {

    //分页查询、
    public List<Chapter> selectBypage(@Param("start") Integer start, @Param("rows")Integer rows,@Param("id") String id);

    //增加章节
    public void insertChapter(Chapter chapter);

    //修改章节信息
    public void updateChapter(Chapter chapter);

    //删除章节信息
    public void deleteChapter(String id);

    //查询总条数
    public Integer  selectCount(String id);


}
