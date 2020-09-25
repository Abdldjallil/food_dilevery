package com.example.myapplication;

import java.util.ArrayList;

public class Request {
    private String phone;
    private String name;
    private String adresse;
    private String total;
    private ArrayList<food_request2>food;
    private String date;
    private String time;



    public Request() {

    }

    public Request(String phone, String name, String adresse, String total, ArrayList<food_request2> food, String date, String time) {
        this.phone = phone;
        this.name = name;
        this.adresse = adresse;
        this.total = total;
        this.food = food;
        this.date = date;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<food_request2> getFood() {
        return food;
    }

    public void setFood(ArrayList<food_request2> food) {
        this.food = food;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

