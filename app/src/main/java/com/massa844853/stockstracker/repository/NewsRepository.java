package com.massa844853.stockstracker.repository;

import android.app.Application;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.database.NewsDao;
import com.massa844853.stockstracker.database.NewsRoomDatabase;
import com.massa844853.stockstracker.models.News;
import com.massa844853.stockstracker.models.NewsImg;
import com.massa844853.stockstracker.utils.NewsResponseCallback;
import com.massa844853.stockstracker.models.NewsResponse;
import com.massa844853.stockstracker.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.NewsApiService;

public class NewsRepository {

    private final Application application;
    private final NewsResponseCallback newsResponseCallback;
    private NewsRoomDatabase newsRoomDatabase;
    private final NewsDao newsDao;

    public NewsRepository(Application context, NewsResponseCallback newsResponseCallback)
    {
        this.application = context;
        this.newsResponseCallback = newsResponseCallback;
        this.newsRoomDatabase = NewsRoomDatabase.getDatabase(application);
        this.newsDao = newsRoomDatabase.newsDao();
    }

    public void start(long lastUpdate) {

        if (System.currentTimeMillis() - lastUpdate > Constants.FRESH_TIMEOUT) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            NewsApiService newsApiService = retrofit.create(NewsApiService.class);

            Call<NewsResponse> jsonCall = newsApiService.getNews("generalnews", "US");

            jsonCall.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    NewsResponse newsResponse = response.body();
                    if (response.body() != null && response.isSuccessful()) {
                        List<NewsImg> newsListImg = Arrays.asList(newsResponse.getItems().getResults());
                        List<News> newsList = new ArrayList<>();
                        for(NewsImg item : newsListImg)
                        {
                            newsList.add(new News(item.getTitle(),item.getLink(),item.getPublisher(),item.getPublished_at(),item.getUrlImage().getResolutions()[0].getUrl()));
                        }
                        saveDataInDatabase(newsList);
                        newsResponseCallback.onResponse(newsList,System.currentTimeMillis());
                    } else {
                        newsResponseCallback.onFailure(application.getString(R.string.error_news));
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    Toast.makeText(application.getApplicationContext(), "Error. " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            readDataFromDatabase(lastUpdate);
        }

    }
    private void saveDataInDatabase(List<News> newsList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                newsDao.deleteAll();
                newsDao.insertNewsList(newsList);
            }
        };
        new Thread(runnable).start();
    }
    private void readDataFromDatabase(long lastUpdate) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                newsResponseCallback.onResponse(newsDao.getAll(), lastUpdate);
            }
        };
        new Thread(runnable).start();
    }
}


