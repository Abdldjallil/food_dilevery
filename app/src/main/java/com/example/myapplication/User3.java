package com.example.myapplication;

public class User3 {
    private int id;
    private String phone;
    private String password;
    public User3()
    {

    }

    public User3(int id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
