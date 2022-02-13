package com.massa844853.stockstracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.massa844853.stockstracker.models.News;
import com.massa844853.stockstracker.models.NewsResponse;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private long lastUpdate = 0;

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public NewsViewModel(@NonNull Application application) {
        super(application);
    }
}
