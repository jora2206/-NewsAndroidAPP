package com.example.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class NewsListActivity extends AppCompatActivity {
    private NewsService newsService = new NewsService();
    private RequestQueue requestQueue;

    private ListView newsListView;

    private NewsAdapter newsArrayAdapter;

    public static interface NewsEvents {
        void onClick(int position);
    }

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
                Log.i(NewsListActivity.this.logTag, "getNews() success");

//                final int newsCount = newsArrayList.size();
//                for (int a = 0; a < newsCount; a++) {
//                    /** Multiply the count of news */
//                    for (int b = 0; b < 10; b++) {
//                        newsArrayList.add(newsArrayList.get(a));
//                    }
//                }

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

    private AdapterView.OnItemClickListener newsItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent newsItemActivityIndent = new Intent(getBaseContext(), NewsItemAcitity.class);
            News news = (News) parent.getItemAtPosition(position);

            newsItemActivityIndent.putExtra("news", news);
            startActivity(newsItemActivityIndent);
        }
    };

    private void displayNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayAdapter = new NewsAdapter(this, newsArrayList);
        this.newsListView.setAdapter(this.newsArrayAdapter);

        this.newsListView.setOnItemClickListener(this.newsItemClickListener);
    }

    private final String logTag = "NewsListActivity";
}
