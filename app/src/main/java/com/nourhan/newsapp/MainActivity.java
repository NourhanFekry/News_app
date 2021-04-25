package com.nourhan.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Article>> {
    RecyclerView newsRV;
    private static String ARTICLE_API;

    private NewsAdapter adapter;
    private ProgressBar progressBar;
    private TextView emptyTextView;
    private ArrayList<Article> articleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        initURI();
        intiViews();
        checkForConnection();

    }

    private void intiViews() {
        newsRV = findViewById(R.id.NewsRV);
        progressBar = findViewById(R.id.progressBar);
        emptyTextView = findViewById(R.id.noDataTV);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        newsRV.setLayoutManager(manager);
        newsRV.hasFixedSize();
        adapter = new NewsAdapter(articleList, this);
        newsRV.setAdapter(adapter);
    }


    private void initURI() {
        Uri.Builder builder = new Uri.Builder();
        // this is the api I used
        // "https://content.guardianapis.com/search?show-tags=contributor&api-key=test&section=fashion"
        builder.scheme("https")
                .authority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("api-key", "test")
                .appendQueryParameter("section", "fashion");
        ARTICLE_API = builder.build().toString();
    }

    private void checkForConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(0, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_internet_connection);
        }
    }

    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ArticleLoader(this, ARTICLE_API);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> data) {
        progressBar.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            articleList.addAll(data);
        } else {
            emptyTextView.setText(R.string.no_data);
        }
        adapter.news = articleList;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Article>> loader) {
        loader.reset();
    }
}