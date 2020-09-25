package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class food_show extends AppCompatActivity {
    private String food_id;
    private String get_price;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private TextView food_title;
    private TextView price_l;
    private TextView price_xl;
    private TextView price_xxl;
    private ImageView food_image;
    private ElegantNumberButton btn_qtt;
    private ArrayList<food_request>food_requests;
    private Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_show);
        food_id=getIntent().getStringExtra("get_food_id");
        btn_qtt=(ElegantNumberButton)findViewById(R.id.btnqtt);
        radioButton1=(RadioButton)findViewById(R.id.rb1);
        radioButton2=(RadioButton)findViewById(R.id.rb2);
        radioButton3=(RadioButton)findViewById(R.id.rb3);
        price_l=(TextView)findViewById(R.id.price1);
        price_xl=(TextView)findViewById(R.id.price2);
        price_xxl=(TextView)findViewById(R.id.price3);
        food_title=(TextView)findViewById(R.id.show_name);
        food_image=(ImageView)findViewById(R.id.show_image);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Food");
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("request");
        db=new Database(this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String p1=(snapshot.child(food_id).child("size").child("l").getValue()).toString();
                String p2=(snapshot.child(food_id).child("size").child("xl").getValue()).toString();
                String p3=(snapshot.child(food_id).child("size").child("xxl").getValue()).toString();
                food_title.setText(snapshot.child(food_id).child("name").getValue().toString());
                Picasso.get().load(snapshot.child(food_id).child("image").getValue().toString()).into(food_image);
                price_l.setText(p1+" DA");
                price_xl.setText(p2+" DA");
                price_xxl.setText(p3+" DA");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    public void select_l(View view) {

        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        get_price=price_l.getText().toString();


        //dedeedederefrf
    }

    public void select_xl(View view) {
        radioButton3.setChecked(false);
        radioButton1.setChecked(false);
        get_price=price_xl.getText().toString();
    }

    public void select_xxl(View view) {
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        get_price=price_xxl.getText().toString();

    }

    public void add_food(View view) {

        String name=food_title.getText().toString();
        String quantity=btn_qtt.getNumber();
        String size=getsize(radioButton1,radioButton2,radioButton3);
        String price=String.valueOf(Integer.parseInt(get_price(radioButton1,radioButton2,radioButton3))*Integer.parseInt(quantity));
        food_request2 food_request=new food_request2(name,size,quantity,price);
        db.add_food(food_request);
        Toast.makeText(food_show.this, "Food was added", Toast.LENGTH_SHORT).show();


    }

    private String getsize(RadioButton radioButton1,RadioButton radioButton2,RadioButton radioButton3)
    {
         String size="";
        if (radioButton1.isChecked())
        {
            size=radioButton1.getText().toString();

        }
        if (radioButton2.isChecked())
        {
            size=radioButton2.getText().toString();
        }
        if (radioButton3.isChecked())
        {
            size=radioButton3.getText().toString();
        }
        return size;

    }
    private String get_price(RadioButton radioButton1,RadioButton radioButton2,RadioButton radioButton3)
    {
        String price= "";
        if (radioButton1.isChecked())
        {
           price=price_l.getText().toString().replaceAll("DA","  ").trim();
        }
        if (radioButton2.isChecked())
        {
            price=price_xl.getText().toString().replaceAll("DA","  ").trim();
        }
        if (radioButton3.isChecked())
        {
            price=price_xxl.getText().toString().replaceAll("DA","  ").trim();

        }

        return price;
    }
}