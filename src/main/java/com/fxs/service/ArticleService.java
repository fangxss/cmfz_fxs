package com.fxs.service;

import com.fxs.entity.Article;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {

    //分页查询
    public HashMap<String,Object> selectBypage(Integer page, Integer rows);

    //删除
    public void deleteArticle(String id);

    //添加
    public HashMap<String,Object> addArticle(Article article);


}
