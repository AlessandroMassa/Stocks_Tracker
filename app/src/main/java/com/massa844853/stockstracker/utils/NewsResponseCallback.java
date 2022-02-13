package com.massa844853.stockstracker.utils;

import com.massa844853.stockstracker.models.News;

import java.util.List;

public interface NewsResponseCallback {
    void onResponse(List<News> newsList, long lastupdate);
    void onFailure(String errorMessage);
}
