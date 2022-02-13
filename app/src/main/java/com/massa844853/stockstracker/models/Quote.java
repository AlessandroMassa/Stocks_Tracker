package com.massa844853.stockstracker.models;

public class Quote {
    private double[] high;
    private double[] low;
    private double[] open;
    private double[] close;
    private String[] volume;

    public Quote(double[] high, double[] low, double[] open, double[] close, String[] volume) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.volume = volume;
    }

    public double[] getHigh() {
        return high;
    }

    public void setHigh(double[] high) {
        this.high = high;
    }

    public double[] getLow() {
        return low;
    }

    public void setLow(double[] low) {
        this.low = low;
    }

    public double[] getOpen() {
        return open;
    }

    public void setOpen(double[] open) {
        this.open = open;
    }

    public double[] getClose() {
        return close;
    }

    public void setClose(double[] close) {
        this.close = close;
    }

    public String[] getVolume() {
        return volume;
    }

    public void setVolume(String[] volume) {
        this.volume = volume;
    }
}
