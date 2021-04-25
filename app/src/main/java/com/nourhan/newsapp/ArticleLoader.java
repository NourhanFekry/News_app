package com.nourhan.newsapp;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    public static final String TAG = "ArticleLoader";
    private String url;
    public ArticleLoader(@NonNull Context context,String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<Article> loadInBackground() {
        if (url == null) return null;
        Log.e(TAG, "loadInBackground: "+ "\n"+url);
        return HttpConnection.getArticleData(url);
    }

    @Override
    protected void onStartLoading() {
        Log.e(TAG, "onStartLoading: " );
        forceLoad();
    }
}

