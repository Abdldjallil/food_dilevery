package com.example.myapplication;

public class this_user {
     private String phone;
     private String password;
     private String name;

     public this_user()
     {

     }

    public this_user(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
