package com.fxs.controller;

import com.fxs.entity.Banner;
import com.fxs.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    //分页查询
    @RequestMapping("/showBypage")
    public HashMap<String,Object> showBypage(Integer page,Integer rows)throws Exception{
        HashMap<String, Object> map = bannerService.selectAll(page, rows);
        return map;
    }

    //增删改
    @RequestMapping("/edit")
    public String edit(Banner banner,String oper,String id) throws Exception{
        String uid=null;
        if(oper.equals("add")){
            uid = bannerService.addBanner(banner);
        }else if(oper.equals("edit")){
            banner.setImg_path(null);
            bannerService.updateBanner(banner);
            uid=banner.getId();
        }else if(oper.equals("del")){
            bannerService.deleteBanner(id);
        }
        return uid;
    }

    //文件上传
    @RequestMapping("/uploadBanner")
    public void uploadBanner(MultipartFile img_path, String id, HttpSession session)throws Exception{
        System.out.println("文件上传");
        String realPath = session.getServletContext().getRealPath("/upload/photo");
        String originalFilename = img_path.getOriginalFilename();
        String imgName=new Date().getTime()+"_"+originalFilename;
        File file = new File(realPath+"/"+imgName);
        if(!file.exists()){
            img_path.transferTo(file);
        }
        Banner banner = new Banner();
        banner.setId(id);
        if(img_path.getOriginalFilename()!=null&&!"".equals(img_path.getOriginalFilename())){
            banner.setImg_path(imgName);
        }
        System.out.println(imgName);
        bannerService.updateBanner(banner);
    }

    //修改状态
    @RequestMapping("/updateStatus")
    public HashMap<String,Object> updateStatus(String id,String status)throws Exception{
        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);
        HashMap<String, Object> map = bannerService.updateBanner(banner);
        return map;
    }
}
