package com.massa844853.stockstracker.models;

import android.media.Image;
import android.provider.ContactsContract;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


public class NewsImg
{

    private String title;
    private String link;
    private String publisher;
    private long published_at;
    private Img main_image;

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

    public Img getUrlImage() {
        return main_image;
    }

    public void setUrlImage(Img main_image) {
        this.main_image = main_image;
    }

    public NewsImg(String title, String link, String publisher, long published_at, Img main_image) {
        this.title = title;
        this.link = link;
        this.publisher = publisher;
        this.published_at = published_at;
        this.main_image = main_image;
    }
}
