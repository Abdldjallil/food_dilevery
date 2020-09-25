package com.example.myapplication;

public class food_request3 {
    private String price;
    private String quantity;
    private String size;


    public food_request3()
    {

    }

    public food_request3(String price, String quantity, String size) {
        this.price = price;
        this.quantity = quantity;
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
