package com.massa844853.stockstracker.models;

public class Chart {
    private Result[] result;

    public Chart(Result[] result) {
        this.result = result;
    }

    public Result[] getResult() {
        return result;
    }

    public void setResult(Result[] result) {
        this.result = result;
    }
}
