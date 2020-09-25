package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class address3 extends AppCompatActivity {

    private String get_name;
    private String get_phone;
    private String total;
    private Database db;
    private String date;
    private String time;
    private history_database db2;
    private DatabaseReference reference;
    private ArrayList<food_request>requests;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address3);
        get_name= Common.current_user.getUsername();
        get_phone=Common.current_user.getPhone();
        total=getIntent().getStringExtra("total");
        date= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        time= new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        db=new Database(this);
          db2=new history_database(this);
        requests=db.get_all_request();
        reference= FirebaseDatabase .getInstance().getReference("request");

    }

    public void finale(View view) {
        final EditText edtaddress =new EditText(this);
        edtaddress.setHint("addresse from google maps.");

        edtaddress.setInputType(3);
       final AlertDialog alertDialog=new AlertDialog.Builder(this)
               .setTitle("finale step")
                .setIcon(R.drawable.addresse_icon)
               .setView(edtaddress)
               .setPositiveButton("request",null)
               .setNegativeButton("Cancel",null)
               .show();

       Button request=alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (edtaddress.getText().toString().trim().isEmpty())
                 {
                     edtaddress.setError("  enter your addresse ");
                 }
                 else
                 {
                      final String addresse=edtaddress.getText().toString();
                     final  ArrayList<food_request2>final_requeste= transform(requests);

                      reference.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {


                             Request final_request=new Request(get_phone,get_name,addresse,total,final_requeste,date,time);
                             reference.child(get_phone).setValue(final_request);
                              finish();
                              Intent intent =new Intent(address3.this,food_category.class);
                              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                              startActivity(intent);


                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {

                          }


                      });

                     historique historique=new historique(get_phone,addresse,total,date,time);
                     db2.add_history(historique);
                     Toast.makeText(address3.this, "Request done", Toast.LENGTH_SHORT).show();
                     alertDialog.dismiss();
                     db.clear_database();
                     requests.clear();



                 }


            }
        });
        Button cancel=alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alertDialog.cancel();
            }
        });



    }
     private ArrayList<food_request2> transform(ArrayList<food_request> requests)
     {
         ArrayList<food_request2>final_request=new ArrayList<>();
         for (int i=0;i<requests.size();i++)
         {
             food_request2 food_request2=new food_request2(requests.get(i).getName(),requests.get(i).getSize(),requests.get(i).getQuantity(),requests.get(i).getPrice()+"DA");
             final_request.add(food_request2);

         }
         return final_request;
     }
}