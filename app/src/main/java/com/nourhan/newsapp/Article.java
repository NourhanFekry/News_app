package com.nourhan.newsapp;


public class Article {
    private String articleTitle;
    private String sectionName;
    private String authorName;
    private String publishDate;
    private String articleUrl;

    public Article(String articleTitle, String sectionName, String authorName, String publishDate, String articleUrl) {
        this.articleTitle = articleTitle;
        this.sectionName = sectionName;
        this.authorName = authorName;
        this.publishDate = publishDate;
        this.articleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}