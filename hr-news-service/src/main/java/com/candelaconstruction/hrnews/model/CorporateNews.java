package com.candelaconstruction.hrnews.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "corporate_news")
public class CorporateNews {

    @Id
    private String id;
    private String title;
    private String date;
    private String category;
    private String readTime;

    @Column(length = 1000)
    private String excerpt;

    @Column(length = 4000)
    private String content;

    public CorporateNews() {}

    public CorporateNews(String id, String title, String date, String category, String readTime, String excerpt, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.readTime = readTime;
        this.excerpt = excerpt;
        this.content = content;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getReadTime() { return readTime; }
    public void setReadTime(String readTime) { this.readTime = readTime; }

    public String getExcerpt() { return excerpt; }
    public void setExcerpt(String excerpt) { this.excerpt = excerpt; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
