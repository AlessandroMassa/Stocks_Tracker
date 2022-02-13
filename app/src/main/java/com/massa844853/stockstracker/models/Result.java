package com.massa844853.stockstracker.models;

public class Result {
    private long[] timestamp;
    private IndicatorPrice indicators;

    public Result(long[] timestamp, IndicatorPrice indicators) {
        this.timestamp = timestamp;
        this.indicators = indicators;
    }

    public long[] getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long[] timestamp) {
        this.timestamp = timestamp;
    }

    public IndicatorPrice getIndicators() {
        return indicators;
    }

    public void setIndicators(IndicatorPrice indicators) {
        this.indicators = indicators;
    }
}
