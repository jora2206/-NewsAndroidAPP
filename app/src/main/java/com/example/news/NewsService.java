package com.example.news;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class NewsService {
    public static interface NewsReady {
        void onNewsReady(ArrayList<News> newsArrayList);
    }

    public static interface NewsError {
        void onNewsError(Exception e);
    }

    private final String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + Config.apiKey;

    public News createMockNews() {
        return new News(
                "George Martynuik",
                "Title",
                "Description",
                "Url",
                "UrlToImage",
                "Content",
                new Date()
        );
    }

    public News[] createMockNewsArray(Integer length) {
        News[] newsArray = new News[length];

        for (int i = 0; i < length; i++) {
            newsArray[0] = this.createMockNews();
        }

        return newsArray;
    }

    public void getNews(
            RequestQueue requestQueue,
            final NewsService.NewsReady newsReady,
            final NewsService.NewsError newsError
    ) {
        final Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            public News parseNewsFromJSON(JSONObject jsonObject) throws JSONException {
                String title = jsonObject.get("title").toString();
                /** @todo make further parse */

                News news = new News(title, "", "", "", "", "", new Date());

                return news;
            }

            @Override
            public void onResponse(JSONObject response) {
                if (response.length() <= 0) {
                    return;
                }

                JSONArray jsonArray = new JSONArray();
                try {
                    jsonArray = response.getJSONArray("articles");
                } catch (Exception e) {}

                ArrayList<News> newsArrayList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        News news = this.parseNewsFromJSON(jsonObject);

                        newsArrayList.add(news);
                    } catch (JSONException e) {
                        Log.e("Volley", "Can not parse JSON", e);

                        newsError.onNewsError(e);
                    }
                }

                newsReady.onNewsReady(newsArrayList);
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Response error", error);

                newsError.onNewsError(error);
            }
        };

        JsonObjectRequest arrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                this.url,
                responseListener,
                errorListener
        );

        requestQueue.add(arrayRequest);
    }
}
