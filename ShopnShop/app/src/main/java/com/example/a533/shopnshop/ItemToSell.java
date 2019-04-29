package com.example.a533.shopnshop;

public class ItemToSell {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String name;
    String quantity;

    public String getIdItem() {
        return idItem;
    }

    String idItem;

    public ItemToSell(String name, String quantity, String idItem) {
        this.name = name;
        this.quantity = quantity;
        this.idItem = idItem;
    }
}
