package com.massa844853.stockstracker.database;

import androidx.room.Insert;

import com.massa844853.stockstracker.models.News;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Query;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insertNewsList(List<News> newsList);

    @Query("DELETE FROM news")
    void deleteAll();



}
