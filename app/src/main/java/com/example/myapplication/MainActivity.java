package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Database db;
    public ArrayList<food_request>requests;

    public Database_user db2;
    public ArrayList<User3>users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Database(this);
        db2=new Database_user(this);
        requests=db.get_all_request();
        users=db2.get_user();
       db.clear_database();
        requests.clear();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!users.isEmpty())
                {

                    Intent intent=new Intent(MainActivity.this,login.class);
                    startActivity(intent);
                    finish();
                }
              if (users.isEmpty())
               {
                    Intent intent=new Intent(MainActivity.this,signup_login.class);
                    startActivity(intent);
                   finish();
                }

            }
        },2000);

    }
}