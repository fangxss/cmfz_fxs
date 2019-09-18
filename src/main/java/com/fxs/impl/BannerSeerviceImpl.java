package com.fxs.impl;

import com.fxs.dao.BannerDao;
import com.fxs.entity.Banner;
import com.fxs.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerSeerviceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public HashMap<String,Object> selectAll(Integer page, Integer rows) {

        Integer pages=(page-1)*rows;
        List<Banner> banners = bannerDao.selectAll(pages,rows);
        HashMap<String, Object> map = new HashMap<>();
        Integer count = bannerDao.selectCount();
        Integer total=count%rows==0 ?count/rows:count/rows+1;
        map.put("records",count);
        map.put("total",total);
        map.put("page",page);
        map.put("rows",banners);
        return map;
    }

    @Override
    public String  addBanner(Banner banner) {
        String ID = UUID.randomUUID().toString();
        banner.setId(ID);
        banner.setStatus("1");
        banner.setUp_date(new Date());
        bannerDao.insertBanner(banner);
        return ID;
    }

    @Override
    public HashMap<String,Object> updateBanner(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(banner);
        try {
        bannerDao.updateBanner(banner);
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
    public void deleteBanner(String id) {
        bannerDao.deleteBanner(id);
    }
}
