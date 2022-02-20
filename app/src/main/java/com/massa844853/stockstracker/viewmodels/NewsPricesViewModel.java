package com.massa844853.stockstracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.massa844853.stockstracker.models.News;
import com.massa844853.stockstracker.models.NewsResponse;
import com.massa844853.stockstracker.models.Price;
import com.massa844853.stockstracker.models.Statistic;
import com.massa844853.stockstracker.models.StockPrice;

import java.util.ArrayList;
import java.util.List;

public class NewsPricesViewModel extends AndroidViewModel {

    private long lastUpdate;
    private List<StockPrice> priceList;
    private List<Statistic> statisticList;
    private int position;
    private String asset;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List<StockPrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<StockPrice> priceList) {
        this.priceList = priceList;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public NewsPricesViewModel(@NonNull Application application) {
        super(application);
        priceList = new ArrayList<>();
        statisticList = new ArrayList<>();
        lastUpdate = 0;
        position = 0;
        asset = "";


    }
}
