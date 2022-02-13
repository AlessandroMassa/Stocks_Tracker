package com.massa844853.stockstracker.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String link;
    private String publisher;
    private long published_at;
    private String main_image;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public News(String title, String link, String publisher, long published_at, String main_image) {
        this.title = title;
        this.link = link;
        this.publisher = publisher;
        this.published_at = published_at;
        this.main_image = main_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getPublished_at() {
        return published_at;
    }

    public void setPublished_at(long published_at) {
        this.published_at = published_at;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }
}
