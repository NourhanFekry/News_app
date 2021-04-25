package com.nourhan.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewHolder> {
    ArrayList<Article> news;
    Context context;

    public NewsAdapter(ArrayList<Article> news, Context context) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, null, false);
        newsViewHolder viewHolder = new newsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
        Article n = news.get(position);
        holder.DataTV.setText(n.getSectionName());
        holder.DateTV.setText(n.getPublishDate());
        holder.TittleTV.setText(n.getArticleTitle());
        holder.writerNameTV.setText(n.getAuthorName());
        holder.itemView.setOnClickListener(v -> {
            Uri uri = Uri.parse(news.get(position).getArticleUrl());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(webIntent);
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    //holder class for newsRecyclerView
    static class newsViewHolder extends RecyclerView.ViewHolder {
        TextView TittleTV;
        TextView DataTV;
        TextView DateTV;
        TextView writerNameTV;

        public newsViewHolder(@NonNull View itemView) {
            super(itemView);
            TittleTV = itemView.findViewById(R.id.TittleTV);
            DataTV = itemView.findViewById(R.id.DataTV);
            DateTV = itemView.findViewById(R.id.DateTV);
            writerNameTV = itemView.findViewById(R.id.writerNameTV);
        }
    }
}
