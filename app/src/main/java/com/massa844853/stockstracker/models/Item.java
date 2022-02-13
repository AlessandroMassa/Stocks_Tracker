package com.massa844853.stockstracker.models;

import okhttp3.Response;

public class Item {
    private NewsImg[] result;

    public Item(NewsImg[] result) {
        this.result = result;
    }

    public NewsImg[] getResults() {
        return result;
    }

    public void setResults(NewsImg[] result) {
        this.result = result;
    }
}
