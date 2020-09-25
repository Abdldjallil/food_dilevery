package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Common.Common;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class login extends AppCompatActivity {
    //__________________________________________DECLARATION OF ALL VARIABLES_______________________________________________________________________//
    private TextInputLayout editphone;
    private TextInputLayout editpassword;
    private DatabaseReference reference ;
    public  FirebaseUser currentuser;
    private ProgressDialog prograssbar;
    private Database_user db;
    public ArrayList<User3>users;
    public FirebaseUser fuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       currentuser=FirebaseAuth.getInstance().getCurrentUser();
        //____________________________________INITIAL ALL VARIBALES________________________________________________________________________________//
        editphone = (TextInputLayout) findViewById(R.id.edt5);
        editpassword = (TextInputLayout) findViewById(R.id.edt6);
        prograssbar=new ProgressDialog(login.this);


        //__________________________________________INITIAL DATABASE_______________________________________________________________________________//
             reference= FirebaseDatabase.getInstance().getReference("User");
             fuser=FirebaseAuth.getInstance().getCurrentUser();
             db=new Database_user(this);
             users=db.get_user();
             if (!users.isEmpty())
             {
                editphone.getEditText().setText(users.get(0).getPhone());
                editpassword.getEditText().setText(users.get(0).getPassword());
             }


    }



    //________________________________________WHEN USER CLICK ON NNEXT BUTTON______________________________________________________________________//

    public void login_event(View view) {


        final String phone = editphone.getEditText().getText().toString().trim();
        final String password = editpassword.getEditText().getText().toString().trim();

        if ((is_not_empty(phone, password)) && (phone_size(phone)) && (password_size(password)) && (phone_start(phone))) {
               prograssbar.show();
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                     if(snapshot.child(phone).exists())
                     {
                         editphone.setErrorEnabled(false);
                         if(snapshot.child(phone).child("password").getValue().equals(password)) {

                            User user=new User (password,phone,snapshot.child(phone).child("username").getValue().toString());

                                 db.clear_database();
                                 User2 user2=new User2(phone,password);
                                  db.add_user(user2);


                             Common.current_user=user;
                             Intent intent = new Intent(login.this, food_category.class);
                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                             startActivity(intent);


                         }
                         else
                         {

                             editpassword.setError("wrong password!!");
                             prograssbar.hide();

                         }

                     }
                     else
                     {

                          editphone.setError("phone not found , go and sign up.");
                          prograssbar.hide();


                     }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {



                }
            });

        } else {
            show_error(editphone, editpassword);
        }

    }

    //_________________________________________WHEN USER CLICK ON BACK BUTTON______________________________________________________________________//

    public void back4(View view) {
        Intent intent = new Intent(login.this, signup_login.class);
        startActivity(intent);
    }

    //_________________________________________WHEN USER CLICK ON SIGN UP TEXTVIEW_________________________________________________________________//

    public void gotosignup(View view) {
        Intent intent = new Intent(login.this, signup.class);
        startActivity(intent);
    }

    //_________________________________________FANCTION TO CHECK IF THE USER IS ENTERING ALL HIS INFORMATION_______________________________________//

    private boolean is_not_empty(String phone, String password) {
        return (!phone.isEmpty()) && (!password.isEmpty());
    }

    //_________________________________________FANCTION TO CHECK PHONE SIZE________________________________________________________________________//

    private boolean phone_size(String phone) {
        return phone.length() == 10;

    }

    //_________________________________________FANCTION TO CHECK PASSWORD SIZE_____________________________________________________________________//

    private boolean password_size(String password) {
        return password.length() >= 6;
    }

    //_________________________________________FANCTION TO SHOW THE EROR'S MESSAGES________________________________________________________________//

    private void show_error(TextInputLayout phone, TextInputLayout password) {
        if (phone.getEditText().getText().toString().trim().isEmpty()) {
            phone.setError("enter your phone");
        }


        if (!phone.getEditText().getText().toString().trim().isEmpty()) {
            if (!phone_size(phone.getEditText().getText().toString().trim())) {
                phone.setError("phone should be with 10 numbers");
            } else {
                phone.setErrorEnabled(false);
            }

        }

        if (!phone.getEditText().getText().toString().trim().isEmpty()) {

            if (phone_size(phone.getEditText().getText().toString().trim())) {

                if (!phone_start(phone.getEditText().getText().toString().trim())) {
                    phone.setError("phone should start with 07...,05...,or 06...");
                } else {
                    phone.setErrorEnabled(false);
                }
            }
        }


        if (password.getEditText().getText().toString().trim().isEmpty()) {
            password.setError("enter your password");
        }


        if (!password.getEditText().getText().toString().trim().isEmpty()) {
            if (!password_size(password.getEditText().getText().toString().trim())) {
                password.setError("password should be more than 6 character");
            } else {
                password.setErrorEnabled(false);
            }
        }

    }

    //_________________________________________FANCTION TO CHECK PHONE FORME_______________________________________________________________________//

    private boolean phone_start(String phone) {
        return (phone.charAt(0) == '0') && ((phone.charAt(1) == '7') || (phone.charAt(1) == '6') || (phone.charAt(1) == '5'));

    }



}