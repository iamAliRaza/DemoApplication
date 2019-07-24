package com.demoapp.Model.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demoapp.Model.Article;
import com.demoapp.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticleRequest {

    private Context context;
    private RequestCallBack requestCallBack;

    public  ArticleRequest ( Context context ){

        this.context = context;
        requestCallBack = (RequestCallBack) context;

    }

    public void getAllArticles (){

        final String URL = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/30.json?api-key="+context.getString(R.string.KEY);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,URL , null,
                response -> {
                    Log.e("Response", response.toString());

                    try{

                        ArticleResponse articleResponse = new ArticleResponse();

                        articleResponse.setStatus(response.getString("status"));

                        JSONArray jsonObjectJSONArray = response.getJSONArray("results");


                        List<Article> articles = new ArrayList<>();

                        for( int i=0; i < jsonObjectJSONArray.length(); i++ ){

                            JSONObject objArticle = jsonObjectJSONArray.getJSONObject(i);

                            Article article = new Article();

                            article.setTitle(objArticle.getString("title"));
                            article.set_abstract(objArticle.getString("abstract"));
                            article.setByline(objArticle.getString("byline"));
                            article.setSource(objArticle.getString("source"));
                            article.setpDate(objArticle.getString("published_date"));

                            articles.add(article);

                        }

                        articleResponse.setArticleList(articles);
                        requestCallBack.onSuccessResponse(articleResponse);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                ,
                error -> {
                    if (!isNetworkAvailable()) {
                        Toast.makeText(context, "Internet is not Connected .. ", Toast.LENGTH_LONG).show();
                    }

                    requestCallBack.onErrorResponse(false);

                })
        {
            /** Passing some request headers* */
            @Override
            public Map<String,String> getHeaders() {

                HashMap<String,String> headers = new HashMap<>();

                headers.put("Content-Type", "application/json");

                return headers;
            }
        };

        Volley.newRequestQueue(context).add(getRequest);

    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    public interface RequestCallBack {

        void onSuccessResponse(ArticleResponse articleResponse);
        void onErrorResponse(boolean status);

    }


}
