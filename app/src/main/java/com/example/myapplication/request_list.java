package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class request_list extends AppCompatActivity {
    private Database db;

    private request_adapter request_adapter;
    private ArrayList<food_request>food_requests;

    private TextView total;
    private DatabaseReference refrerenc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        refrerenc=FirebaseDatabase.getInstance().getReference("request");
        Button requeste=(Button)findViewById(R.id.btn_request);
        String get_phone = Common.current_user.getPhone();
        total=(TextView)findViewById(R.id.total);
        total.setText(R.string.daa);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myrv3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=new Database(this);

        food_requests=db.get_all_request();
        if (food_requests.isEmpty())
        {
            requeste.setEnabled(false);


        }
        else
        {
            requeste.setEnabled(true);

        }
        request_adapter=new request_adapter(this,food_requests);
        recyclerView.setAdapter(request_adapter);
        calculate();

        //______________________________________________WHEN THE USER CLICK ON DELETE REQUEST______________________________________________//

        request_adapter.setOnItemClickListener(new request_adapter.OnItemClikLIstener() {
            @Override
            public void OnItemDelete(int possition) {
             int id=food_requests.get(possition).getId();
               db.delet_request(id);
                 remove_item(possition);
                calculate();


            }
        });


    }




    //_______________________________________________WHEN THE USER CLICK ON REQUEST BUTTON_________________________________________//
    public void requeste(View view)
    {
        refrerenc.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

                Intent intent =new Intent(request_list.this,address.class);
                intent.putExtra("total",total.getText().toString());
                startActivity(intent);

            }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });



    }

    //_______________________________________________FANCTION TO REMOVE REQUEST FROM RECYCLER VIEW_________________________________________//
    public void remove_item(int possiton)
    {
        food_requests.remove(possiton);
        request_adapter.notifyItemRemoved(possiton);
    }

    //_______________________________________________FANCTION TO CALCULATETOTAL_________________________________________//
    public void calculate()
    {

        if (!food_requests.isEmpty()) {
            int total_price = 0;

            for (int i = 0; i < food_requests.size(); i++) {
                total_price = total_price + Integer.parseInt(food_requests.get(i).getPrice());
            }   total.setText(String.valueOf(total_price)+"DA");

        }
        else
        {
            total.setText(R.string.daa);
        }

    }





}