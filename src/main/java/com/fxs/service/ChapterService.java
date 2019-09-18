package com.fxs.service;

import com.fxs.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface ChapterService {

    //查询所有数据
    public HashMap<String,Object> selectAll(Integer page, Integer rows,String albumId);

    //添加数据
    public  String addChapter(Chapter chapter);

    //修改数据
    public  HashMap<String,Object> updateChapter(Chapter chapter);

    //删除数据
    public void deleteChapter(String id);

    //上传音频
    public void uploadChapter(MultipartFile url, String id, HttpSession session)throws Exception;

}
