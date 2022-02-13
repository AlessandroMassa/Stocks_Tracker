package com.massa844853.stockstracker.models;

public class IndicatorPrice {
    private Quote[] quote;
    private AdjVec[] adjclose;


    public IndicatorPrice(Quote[] quote, AdjVec[] adjclose) {
        this.quote = quote;
        this.adjclose = adjclose;
    }

    public Quote[] getQuote() {
        return quote;
    }

    public void setQuote(Quote[] quote) {
        this.quote = quote;
    }

    public AdjVec[] getAdjclose() {
        return adjclose;
    }

    public void setAdjclose(AdjVec[] adjclose) {
        this.adjclose = adjclose;
    }
}
