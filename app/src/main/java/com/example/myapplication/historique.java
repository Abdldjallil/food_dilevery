package com.example.myapplication;

public class historique {
    private String phone;
    private String addresse;
    private String total;
    private String date;
    private String time;

    public historique()
    {

    }

    public historique(String phone, String addresse, String total, String date, String time) {
        this.phone = phone;
        this.addresse = addresse;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
