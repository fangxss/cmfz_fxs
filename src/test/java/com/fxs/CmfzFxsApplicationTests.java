package com.fxs;

import com.fxs.dao.BannerDao;
import com.fxs.entity.Admin;
import com.fxs.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzFxsApplicationTests {

    @Resource
    private BannerDao bannerDao;

    @Test
    public void testEntity() {
        List<Banner> banners = bannerDao.selectAll(2, 3);
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

}
