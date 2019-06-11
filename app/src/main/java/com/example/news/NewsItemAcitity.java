package com.example.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsItemAcitity extends AppCompatActivity {
    private News news;

    private ImageView imgViewRef;
    private TextView titleViewRef;
    private TextView contentViewRef;
    private TextView dateViewRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item_acitity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.news = (News) getIntent().getSerializableExtra("news");

        this.initViews();

        this.setViews();

        this.news = this.news;
    }

    private void initViews() {
        this.imgViewRef = (ImageView) findViewById(R.id.img);
        this.titleViewRef = (TextView) findViewById(R.id.title);
        this.contentViewRef = (TextView) findViewById(R.id.content);
        this.dateViewRef = (TextView) findViewById(R.id.date);
    }

    private void setViews() {
        String dateString = this.news.publishedAt.toGMTString();

        Picasso.with(this).load(this.news.urlToImage).into(this.imgViewRef);
        this.titleViewRef.setText(this.news.title);
        this.contentViewRef.setText(this.news.content);
        this.dateViewRef.setText(dateString);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
