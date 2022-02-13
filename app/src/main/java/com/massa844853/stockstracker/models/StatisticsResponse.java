package com.massa844853.stockstracker.models;

public class StatisticsResponse {
    private Price price;
    private KeyStatistics defaultKeyStatistics;

    public Price getPrice() {
        return price;
    }

    public StatisticsResponse(Price price, KeyStatistics defaultKeyStatistics) {
        this.price = price;
        this.defaultKeyStatistics = defaultKeyStatistics;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public KeyStatistics getDefaultKeyStatistics() {
        return defaultKeyStatistics;
    }

    public void setDefaultKeyStatistics(KeyStatistics defaultKeyStatistics) {
        this.defaultKeyStatistics = defaultKeyStatistics;
    }
}
