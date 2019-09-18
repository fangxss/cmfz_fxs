package com.fxs.controller;

import com.fxs.entity.Article;
import com.fxs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("showBypage")
    public HashMap<String,Object> showBypage(Integer page,Integer rows)throws Exception{
        HashMap<String, Object> map = articleService.selectBypage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(String oper, String id)throws Exception{
        if(oper.equals("del")){
            articleService.deleteArticle(id);
        }
        return "成功";
    }
    @RequestMapping("update")
    public HashMap<String, Object> update(Article article, String ArticleId){
        article.setId(ArticleId);
        System.out.println("==执行修改操作=="+article);

        return null;
    }

    @RequestMapping("add")
    public HashMap<String, Object> add(Article article){
        System.out.println("==执行添加操作=="+article);
        HashMap<String, Object> map = articleService.addArticle(article);
        return map;
    }

}
