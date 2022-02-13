package com.massa844853.stockstracker.models;

public class AdjVec {
    private double[] adjclose;

    public AdjVec(double[] adjclose) {
        this.adjclose = adjclose;
    }

    public double[] getAdjclose() {
        return adjclose;
    }

    public void setAdjclose(double[] adjclose) {
        this.adjclose = adjclose;
    }
}
