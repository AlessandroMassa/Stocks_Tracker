package com.massa844853.stockstracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.massa844853.stockstracker.models.News;
import com.massa844853.stockstracker.utils.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {News.class}, version = Constants.DATABASE_VERSION)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();

    private static volatile NewsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsRoomDatabase.class, Constants.NEWS_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}