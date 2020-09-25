package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signup_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);
    }

    public void go_to_login_page(View view) {

        Intent intent =new Intent(signup_login.this,login.class);
        startActivity(intent);

    }

    public void go_to_signup_page(View view) {
        Intent intent =new Intent(signup_login.this,signup.class);
        startActivity(intent);
    }
}