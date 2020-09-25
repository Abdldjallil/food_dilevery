package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class historique_adapter extends RecyclerView.Adapter<historique_adapter.hviewholder> {

    Context c;
    List<historique2>historiques;

    public historique_adapter (Context context,List<historique2> list)
    {
        c=context;
        historiques=list;
    }
    @NonNull
    @Override
    public historique_adapter.hviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(c).inflate(R.layout.row_history,parent,false);
       return new hviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historique_adapter.hviewholder holder, int position) {
            historique2 h=historiques.get(position);
            holder.id.setText(String.valueOf(h.getId()));
            holder.phone.setText(h.getPhone());
            holder.addresse.setText(h.getAddresse());
            holder.total.setText(h.getTotal());
            holder.date.setText(h.getDate());
            holder.time.setText(h.getTime());
    }

    @Override
    public int getItemCount() {
        return historiques.size();
    }

    public static class hviewholder extends RecyclerView.ViewHolder{

          TextView id;
          TextView phone;
          TextView addresse;
          TextView total;
          TextView date;
          TextView time;

        public hviewholder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.hid);
           phone=(TextView)itemView.findViewById(R.id.hphone);
            addresse=(TextView)itemView.findViewById(R.id.haddresse);
            total=(TextView)itemView.findViewById(R.id.htotal);
            date=(TextView)itemView.findViewById(R.id.hdate);
            time=(TextView)itemView.findViewById(R.id.htime);
        }
    }
}
