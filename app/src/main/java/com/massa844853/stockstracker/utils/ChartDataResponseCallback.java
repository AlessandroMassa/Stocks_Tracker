package com.massa844853.stockstracker.utils;

import com.massa844853.stockstracker.models.StockPrice;

import java.util.List;

public interface ChartDataResponseCallback {
    void onResponsePrices(List<StockPrice> pricesList);
    void onFailure(String errorMessage);
}
