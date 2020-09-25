package com.example.myapplication;

public class food_request2 {
    private String name;
    private String size;
    private String quantity;
    private String price;
    public food_request2()
    {

    }

    public food_request2(String name, String size, String quantity, String price) {
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
