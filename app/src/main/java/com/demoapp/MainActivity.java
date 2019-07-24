package com.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.demoapp.Model.Article;
import com.demoapp.Model.network.ArticleRequest;
import com.demoapp.Model.network.ArticleResponse;
import com.demoapp.View.ViewImplementer;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ArticleRequest.RequestCallBack, AdapterView.OnItemClickListener {

    private ViewImplementer viewImplementer;
    private ArticleRequest articleRequest;
    private ArticleResponse articleResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewImplementer = new ViewImplementer(this);
        viewImplementer.initViews();

        viewImplementer.showProgress();
        articleRequest = new ArticleRequest(this);
        articleRequest.getAllArticles();



    }

    public ArticleRequest getArticleRequest(){
        return articleRequest;
    }

    @Override
    public void onSuccessResponse(ArticleResponse articleResponse) {

        this.articleResponse = articleResponse;
        viewImplementer.showList(articleResponse.getArticleList());
        viewImplementer.hideProgress();
    }

    @Override
    public void onErrorResponse(boolean status) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        List<Article> list = articleResponse.getArticleList();

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("title",list.get(position).getTitle());
        intent.putExtra("abs",list.get(position).get_abstract());
        intent.putExtra("date",list.get(position).getpDate());
        intent.putExtra("byLine",list.get(position).getByline());
        intent.putExtra("source",list.get(position).getSource());

        startActivity(intent);
    }
}
