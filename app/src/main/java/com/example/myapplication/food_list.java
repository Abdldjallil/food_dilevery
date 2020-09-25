package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class food_list extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        reference2= FirebaseDatabase.getInstance().getReference("Food");
        recyclerView2=(RecyclerView)findViewById(R.id.myrv2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Food>option=new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(reference2.orderByChild("menuid").equalTo(getIntent().getStringExtra("get_category_key")),Food.class)
                .build();


        FirebaseRecyclerAdapter<Food,myivewholder2> adapter=new FirebaseRecyclerAdapter<Food, myivewholder2>(option) {
            @Override
            protected void onBindViewHolder(@NonNull myivewholder2 holder, final int position, @NonNull Food model) {

                holder.name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.image);


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(food_list.this,food_show.class);
                        intent.putExtra("get_food_id",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public myivewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
              return new myivewholder2(view);
            }
        };
        recyclerView2.setAdapter(adapter);
        adapter.startListening();
    }

    public static class myivewholder2 extends  RecyclerView.ViewHolder
    {
         TextView name;
         ImageView image;


        public myivewholder2(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.list_name);
            image=(ImageView)itemView.findViewById(R.id.list_image);
        }
    }
}