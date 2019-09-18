package com.fxs.controller;

import com.fxs.entity.Chapter;
import com.fxs.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("show")
    public HashMap<String, Object> show(Integer page, Integer rows, String albumId) throws Exception {
        HashMap<String, Object> map = chapterService.selectAll(page, rows, albumId);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Chapter chapter, String oper, String albumId) {
        String uid = null;
        if (oper.equals("add")) {
            chapter.setAlbum_id(albumId);
            uid = chapterService.addChapter(chapter);
        } else if (oper.equals("del")) {
            chapterService.deleteChapter(chapter.getId());
        }
        return uid;
    }

    @RequestMapping("/uploadChapter")
    public String uploadChapter(MultipartFile url, String id, HttpSession session) throws Exception {
        String message = null;
        System.out.println("==上传文件==开始");
        chapterService.uploadChapter(url, id, session);
        return message;
    }

    @RequestMapping("downloadChapter")
    public  void downloadChapter(String filename, HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getSession().getServletContext().getRealPath("/upload/music");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(realPath, filename));

            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(fileInputStream,outputStream);
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}