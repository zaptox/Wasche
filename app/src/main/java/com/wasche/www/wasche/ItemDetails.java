package com.wasche.www.wasche;

public class ItemDetails {

    private String item_name, quantity, price;

    public ItemDetails(){

    }

    public ItemDetails(String item_name, String quantity, String price){
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
