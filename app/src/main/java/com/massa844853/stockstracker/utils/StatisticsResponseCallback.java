package com.massa844853.stockstracker.utils;

import com.massa844853.stockstracker.models.Statistic;
import com.massa844853.stockstracker.models.StatisticValue;

import java.util.List;

public interface StatisticsResponseCallback {
    void onResponse(List<Statistic> eleStatistic);
    void onFailure(String errorMessage);
}
