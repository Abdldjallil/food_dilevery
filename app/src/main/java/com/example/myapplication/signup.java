package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapplication.Common.Common;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    private TextInputLayout edtusername;
    private TextInputLayout edtphone;
    private TextInputLayout edtpassword;
    private DatabaseReference reference;
    private Database_user db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //________________________________________________DECLARE ALL VARIABLE __________________________________________________//
        setContentView(R.layout.activity_signup);
        edtusername=(TextInputLayout)findViewById(R.id.edt1);
        edtphone=(TextInputLayout)findViewById(R.id.edt2);
        edtpassword=(TextInputLayout)findViewById(R.id.edt3);
        reference= FirebaseDatabase .getInstance().getReference("User");
        db=new Database_user(this);





    }



    //______________________________________________________WHEN HE USER CLICK ON NEXT BUTTON______________________________________//
    public void next_btn_event(View view) {
        final String username=edtusername.getEditText().getText().toString().trim();
        final String phone=edtphone.getEditText().getText().toString().trim();
        final String password=edtpassword.getEditText().getText().toString().trim();

        if(is_empty(username,phone,password)&&(password_size(password))&&(phone_start(phone))&&(phone_size(phone)))
        {
               reference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.child(phone).exists())
                       {
                           edtphone.setError("phone already exists just go and Login.");
                       }
                 else
                       {
                           edtphone.setErrorEnabled(false);
                           User user=new User(password,phone,username);
                           Common.current_user=user;
                           reference.child(phone).setValue(user);
                           db.clear_database();
                           User2 user2 =new User2(phone,password);
                           db.add_user(user2);
                           Intent intent=new Intent(signup.this,food_category.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);

                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });


        }
        else
        {
            show_errors(edtusername,edtphone,edtpassword);
        }

    }

    //______________________________________________________WHEN HE USER CLICK ON LOGIN TEXT BUTTON____________________________________//

    public void gotologin(View view) {
        Intent intent=new Intent(signup.this,login.class);
        startActivity(intent);
    }
    //_____________________________________________________FANCTION TO CHECK IF THE USER ID PUTING ALL HIS INFORMATION____________//
    private boolean is_empty(String username,String phone,String password)
    {
        return (!username.isEmpty()) && (!phone.isEmpty()) && (!password.isEmpty());
    }

    //___________________________________________________FANCTION TO ADD +213 TO THE PHONE NUMBER_________________________________//
    private String  finale_phone_form (String phone)
    {
        return phone.replaceFirst("0","+213");
    }

    //____________________________________________________FANCTION TO CHECK PHONE SIZE_____________________________________________//

    private boolean phone_size (String phone)
    {
        return phone.length() == 10;
    }


    //______________________________________________________FANCTION TO CHECK IF PHONE START WITH 07 ,06 ,05 _______________________//
    private boolean phone_start(String phone)
    {
        return (phone.charAt(0) == '0') && (phone.charAt(1) == '7') || (phone.charAt(1) == '5') || (phone.charAt(1) == '6');


    }

    //______________________________________________________FANCTION TO CHECK PASSWORD SIZE________________________________________//

    private boolean password_size(String password)
    {
        return password.length() >= 6;
    }

    //______________________________________________________FANCTION TO SHOW ALL THE ERRORS_______________________________________//
    private void show_errors(TextInputLayout edtusername,TextInputLayout edtphone,TextInputLayout edtpassword)
    {

        String username=edtusername.getEditText().getText().toString().trim();
        String phone=edtphone.getEditText().getText().toString().trim();
        String password=edtpassword.getEditText().getText().toString().trim();

        if(username.isEmpty())
        {
            edtusername.setError("enter your name");

        }
        if (!username.isEmpty())
        {
            edtusername.setErrorEnabled(false);
        }



        if(password.isEmpty())
        {
            edtpassword.setError("enter password");
        }
        if (!password.isEmpty())
        {
            edtpassword.setErrorEnabled(false);
        }

        if (phone.isEmpty()) {
            edtphone.setError("enter your phone");
        }



        if (!phone.isEmpty()) {
            if (!phone_size(phone)) {
                edtphone.setError("phone should be with 10 numbers");
            } else {
                edtphone.setErrorEnabled(false);
            }

        }

        if (!phone.isEmpty()) {

            if (phone_size(phone)) {

                if (!phone_start(phone)) {
                    edtphone.setError("phone should start with 07...,05...,or 06...");
                } else {
                    edtphone.setErrorEnabled(false);
                }
            }
        }


        if(!password.isEmpty())
        {
            if(!password_size(password))
            {
                edtpassword.setError("required 6 numbers or more");

            }
            else
            {
                edtpassword.setErrorEnabled(false);
            }
        }



    }

    public void back1(View view) {
        Intent intent=new Intent(signup.this,signup_login.class);
        startActivity(intent);
    }

}