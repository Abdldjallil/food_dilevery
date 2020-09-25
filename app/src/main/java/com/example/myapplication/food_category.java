package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class food_category extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private Database db2;
    private history_database db;
    private ArrayList<food_request>food_requests;
    public ArrayList<historique2>historiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_ctegory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db2=new Database(this);
        db=new history_database(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView=(RecyclerView)findViewById(R.id.myrv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //_________________________________________________INITIAL DATABASE________________________________________________//
        reference= FirebaseDatabase.getInstance().getReference("Category");

        //_________________________________________________WHEN THE USER CLICK ON SHOPIN BUTTON___________________________//

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                food_requests=db2.get_all_request();
                    if (food_requests.isEmpty())
                    {
                        Toast.makeText(food_category.this, "select your foods first", Toast.LENGTH_SHORT).show();
                    }
                    if(!food_requests.isEmpty()) {
                        Intent intent = new Intent(food_category.this, request_list.class);
                        startActivity(intent);
                    }


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Category> options=new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(reference,Category.class)
                .build();

        FirebaseRecyclerAdapter<Category,myviewholder> adapter=new FirebaseRecyclerAdapter<Category, myviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull Category model) {

                holder.name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.image);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(food_category.this,food_list.class);
                       intent.putExtra("get_category_key",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category,parent,false);
                return new myviewholder(view);

            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    public static  class myviewholder extends RecyclerView.ViewHolder
    {
         TextView name;
         ImageView image;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
             name=(TextView)itemView.findViewById(R.id.category_name);
             image=(ImageView)itemView.findViewById(R.id.category_image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Logout:{

                Intent intent=new Intent (food_category.this,login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                    break;
            }
            case R.id.History:{
                    historiques=db.get_all_history();
                    if (historiques.isEmpty())
                    {
                        Toast.makeText(this, "no history ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        // db2.clear_database();
                        // food_requests.clear();
                        Intent intent = new Intent(food_category.this, history.class);
                        startActivity(intent);

                    }


                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }
}