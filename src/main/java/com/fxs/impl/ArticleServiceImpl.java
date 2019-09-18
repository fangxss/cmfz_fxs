package com.fxs.impl;

import com.fxs.dao.ArticleDao;
import com.fxs.entity.Article;
import com.fxs.entity.User;
import com.fxs.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @Override
    public HashMap<String, Object> selectBypage(Integer page, Integer rows) {
        Integer pages=(page-1)*rows;
        List<Article> articles = articleDao.selectBypage(pages, rows);
        Integer count = articleDao.selectCount();
        HashMap<String, Object> map = new HashMap<>();
        Integer total=count % rows==0 ? count/rows :count /rows +1;
        map.put("records",count);
        map.put("total",total);
        map.put("page",page);
        map.put("rows",articles);
        return map;
    }

    @Override
    public void deleteArticle(String id) {
        articleDao.deleteArticle(id);
    }

    @Override
    public HashMap<String, Object> addArticle(Article article) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            article.setId(UUID.randomUUID().toString());
            article.setGuruid("上市");
            article.setUp_date(new Date());
            articleDao.addArticle(article);
            map.put("message","成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","失败");
        }

        return map;
    }
}
