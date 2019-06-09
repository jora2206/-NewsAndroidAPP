package com.example.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class NewsListActivity extends AppCompatActivity {
    private NewsService newsService = new NewsService();
    private RequestQueue requestQueue;

    private ListView newsListView;

    private ArrayAdapter<News> newsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        this.initUtils();

        this.initViewRefs();

        this.initNews();
    }

    private void initUtils() {
        this.requestQueue = Volley.newRequestQueue(this);
    }

    private void initViewRefs() {
        newsListView = findViewById(R.id.news_list_view);
    }

    public void initNews() {
        this.getNews();
    }

    private void getNews() {
        final NewsService.NewsReady newsReady = new NewsService.NewsReady() {
            @Override
            public void onNewsReady(ArrayList<News> newsArrayList) {
                displayNewsArrayList(newsArrayList);
            }
        };
        
        final NewsService.NewsError newsError = new NewsService.NewsError() {
            @Override
            public void onNewsError(Exception e) {
                Log.e(NewsListActivity.this.logTag, "getNews() error", e);
            }
        };
        
        newsService.getNews(this.requestQueue, newsReady, newsError);
    }

    private void displayNewsArrayList(ArrayList<News> newsArrayList) {
        /** @todo build custom adapter and display through it */
    }

    private final String logTag = "NewsListActivity";
}
