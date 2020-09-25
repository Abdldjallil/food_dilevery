package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class request_adapter extends  RecyclerView.Adapter<request_adapter.lastviewholder> {
  private OnItemClikLIstener mlistener;
    public interface OnItemClikLIstener
    {
      void OnItemDelete(int possition);
    }

    public void setOnItemClickListener(OnItemClikLIstener listener){
        mlistener=listener;
    }



     public static class lastviewholder extends RecyclerView .ViewHolder{
         TextView id;
         TextView name;
         TextView size;
         TextView quantity;
         TextView price;
         ImageView btndelete;


         public lastviewholder(@NonNull final View itemView, final OnItemClikLIstener lIstener)  {
             super(itemView);
             id=(TextView)itemView.findViewById(R.id.id_tv);
             name=(TextView)itemView.findViewById(R.id.request_name);
             size=(TextView)itemView.findViewById(R.id.request_size);
             quantity=(TextView)itemView.findViewById(R.id.request_quantity);
             price=(TextView)itemView.findViewById(R.id.request_price);
             btndelete=(ImageView)itemView.findViewById(R.id.delet_btn);

             btndelete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if(lIstener!=null)
                     {
                         int possition =getAdapterPosition();
                         if (possition!=RecyclerView.NO_POSITION)
                         {
                             lIstener.OnItemDelete(possition);
                         }
                     }
                 }
             });


         }


     }
      private Context c;
       private List<food_request>food_requests;

     public request_adapter(Context context, List<food_request>list)
     {
        this. c=context;
         food_requests=list;

     }

    @NonNull
    @Override
    public request_adapter.lastviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(c).inflate(R.layout.row_request,parent,false);
        return new lastviewholder(view,mlistener);

    }

    @Override
    public void onBindViewHolder(@NonNull final request_adapter.lastviewholder holder, final int position) {
      food_request f= food_requests.get(position);
      holder.id.setText (String.valueOf(f.getId()));
      holder.name.setText(f.getName());
      holder.size.setText(f.getSize());
      holder.quantity.setText(f.getQuantity());
      holder.price.setText(f.getPrice()+"DA");




    }

    @Override
    public int getItemCount() {
        return food_requests.size();
    }
}
