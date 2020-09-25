package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;


import java.util.ArrayList;

public class history extends AppCompatActivity {
    public RecyclerView recyclerView;
    public historique_adapter adapter;
    public ArrayList<historique2>historiques;
    public history_database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

      db=new history_database(this);
      historiques=db.get_all_history();

      recyclerView=(RecyclerView)findViewById(R.id.hrv);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      adapter=new historique_adapter(this,historiques);
      recyclerView.setAdapter(adapter);








    }
}