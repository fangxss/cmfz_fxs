package com.fxs.dao;

import com.fxs.entity.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleDao {

    //分页查询
    public List<Article> selectBypage(Integer start,Integer rows);

    //删除
    public void deleteArticle (String id);

    //查询总条数
    public Integer  selectCount();

    //添加
    public void addArticle(Article article);


}
