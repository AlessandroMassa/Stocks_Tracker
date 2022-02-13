package com.massa844853.stockstracker.models;

import com.google.gson.annotations.SerializedName;

public class KeyStatistics {
    private StatisticValue sharesOutstanding;
    private StatisticValue beta;
    @SerializedName("52WeekChange")
    private StatisticValue _52WeekChange;
    private StatisticValue enterpriseValue;
    private StatisticValue lastDividendDate;

    public KeyStatistics(StatisticValue sharesOutstanding, StatisticValue beta, StatisticValue _52WeekChange, StatisticValue enterpriseValue, StatisticValue lastDividendDate) {
        this.sharesOutstanding = sharesOutstanding;
        this.beta = beta;
        this._52WeekChange = _52WeekChange;
        this.enterpriseValue = enterpriseValue;
        this.lastDividendDate = lastDividendDate;
    }

    public StatisticValue getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(StatisticValue sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public StatisticValue getBeta() {
        return beta;
    }

    public void setBeta(StatisticValue beta) {
        this.beta = beta;
    }

    public StatisticValue get_52WeekChange() {
        return _52WeekChange;
    }

    public void set_52WeekChange(StatisticValue _52WeekChange) {
        this._52WeekChange = _52WeekChange;
    }

    public StatisticValue getEnterpriseValue() {
        return enterpriseValue;
    }

    public void setEnterpriseValue(StatisticValue enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
    }

    public StatisticValue getLastDividendDate() {
        return lastDividendDate;
    }

    public void setLastDividendDate(StatisticValue lastDividendDate) {
        this.lastDividendDate = lastDividendDate;
    }
}
