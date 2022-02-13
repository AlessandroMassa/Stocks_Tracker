package com.massa844853.stockstracker.models;

public class Price {
    private String shortName;
    private String marketState;
    private String quoteType;
    private StatisticValue marketCap;

    public Price(String shortName, String marketState, String quoteType, StatisticValue marketCap) {
        this.shortName = shortName;
        this.marketState = marketState;
        this.quoteType = quoteType;
        this.marketCap = marketCap;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMarketState() {
        return marketState;
    }

    public void setMarketState(String marketState) {
        this.marketState = marketState;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public StatisticValue getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(StatisticValue marketCap) {
        this.marketCap = marketCap;
    }
}
