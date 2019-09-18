package com.fxs.impl;

import com.fxs.dao.AlbumDao;
import com.fxs.entity.Album;
import com.fxs.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumDao albumDao;

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Override
    public HashMap<String, Object> selectAll(Integer page, Integer rows) {
        Integer pages=(page-1)*rows;
        System.out.println(pages+"============="+rows);
        List<Album> albums = albumDao.selectBypage(pages, rows);
        Integer count = albumDao.selectCount();
        Integer total=count%rows==0 ? count /rows :count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("records",count);
        map.put("total",total);
        map.put("page",page);
        map.put("rows",albums);
        return map;
    }

    @Override
    public String addAlbum(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setPub_date(new Date());
        albumDao.insertAlbum(album);
        return s;
    }

    @Override
    public HashMap<String, Object> updateAlbum(Album album) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(album);
        try {
            albumDao.updateAlbum(album);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }

    @Override
    public void deleteAlbum(String id) {

    }
}
