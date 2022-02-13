package com.massa844853.stockstracker.models;

import java.util.Date;

public class StockPrice
{
    private Date date;
    private double adjPrice;
    private String volume;
    private double high;
    private double low;
    private double open;
    private double close;

    public StockPrice(Date date, double adjPrice, String volume, double high, double low, double open, double close) {
        this.date = date;
        this.adjPrice = adjPrice;
        this.volume = volume;
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAdjPrice() {
        return adjPrice;
    }

    public void setAdjPrice(double adjPrice) {
        this.adjPrice = adjPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
