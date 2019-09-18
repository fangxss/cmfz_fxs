package com.fxs.controller;

import com.fxs.entity.Album;
import com.fxs.entity.Banner;
import com.fxs.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showBypage")
    private HashMap<String,Object> showBypage(Integer page,Integer rows)throws Exception{
        System.out.println("123123123");
        HashMap<String, Object> map = albumService.selectAll(page, rows);
        return map;
    }

    //增删改
    @RequestMapping("/edit")
    public String edit(Album album, String oper) throws Exception{
        String uid=null;
        System.out.println("12121212");
        if(oper.equals("add")){
            uid = albumService.addAlbum(album);
        }else if(oper.equals("edit")){
            album.setCover_img(null);
            albumService.updateAlbum(album);
            uid=album.getId();
        }
        return uid;
    }

    //文件上传
    @RequestMapping("/uploadAlbum")
    public void uploadBanner(MultipartFile cover_img, String id, HttpSession session)throws Exception{
        System.out.println("文件上传");
        String realPath = session.getServletContext().getRealPath("/upload/img");
        String originalFilename = cover_img.getOriginalFilename();
        String imgName=new Date().getTime()+"_"+originalFilename;
        File file = new File(realPath+"/"+imgName);
        if(!file.exists()){
            cover_img.transferTo(file);
        }
        Album album = new Album();
        album.setId(id);
        if(cover_img.getOriginalFilename()!=null&&!"".equals(cover_img.getOriginalFilename())){
            album.setCover_img(imgName);
        }
        System.out.println(imgName);
        albumService.updateAlbum(album);
    }

}
