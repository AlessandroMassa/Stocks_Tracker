package com.massa844853.stockstracker.models;

public class ChartDataResponse
{
    private  Chart chart;

    public ChartDataResponse(Chart chart) {
        this.chart = chart;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

}
