package com.demoapp.Model.network;

import com.demoapp.Model.Article;

import java.util.List;

public class ArticleResponse {

    private List<Article> articleList;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
