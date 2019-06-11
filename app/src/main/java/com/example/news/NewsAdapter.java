package com.example.news;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.squareup.picasso.Picasso;

public class NewsAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<News> newsArrayList;

    private News news;

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.ctx = context;
        this.newsArrayList = newsArrayList;
        this.lInflater = (LayoutInflater) this.ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Count of elements
    @Override
    public int getCount() {
        return this.newsArrayList.size();
    }

    // Get element by position (index)
    @Override
    public News getItem(int position) {
        return this.newsArrayList.get(position);
    }

    // Get id by position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Render row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.activity_news_list_row, parent, false);
        }

        News news = this.getItem(position);

        // Set view items

        this.news = news;

        ImageView imageView = ((ImageView) view.findViewById(R.id.imageView));
        Picasso.with(this.ctx).load(news.urlToImage).into(imageView);
        ((TextView) view.findViewById(R.id.title)).setText(news.title);
        ((TextView) view.findViewById(R.id.description)).setText(news.description);

        return view;
    }


}
