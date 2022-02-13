package com.massa844853.stockstracker.models;

public class NewsResponse {

    public NewsResponse(Item item) {
        this.items = item;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item item) {
        this.items = item;
    }

    private Item items;
}
