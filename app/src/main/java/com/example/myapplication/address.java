package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Common.Common;

public class address extends AppCompatActivity {

    private String total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        total=getIntent().getStringExtra("total");

    }

    public void adress1(View view) {
        Intent intent=new Intent(address.this,address3.class);

        intent.putExtra("total",total);
        startActivity(intent);
    }


}