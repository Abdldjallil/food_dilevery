package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final  String DB_name="Food_db";
    private static  final String DB_table="food";
    private static final int DB_ver=1;
    public Database(@Nullable Context context) {
        super(context, DB_name, null, DB_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table food (id Integer primary key, name varchar(40),size varchar(20),quantity varchar(20),price varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP Table food ");
       onCreate(sqLiteDatabase);
    }

   //_________________________________________________ADD A REQUEST___________________________________________________//
    public void add_food(food_request2 food)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",food.getName());
        values.put("size",food.getSize());
        values.put("quantity",food.getQuantity());
        values.put("price",food.getPrice());

        db.insert("food",null,values);


    }


    //________________________________________GET ALL REQUESTS IN ARRAYLIST____________________________________________//
    public ArrayList<food_request> get_all_request()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<food_request>food_requests=new ArrayList<food_request>();
        Cursor cursor=db.rawQuery("select * from food",null);

        if(cursor.moveToFirst()) {
            do {
                int  id=cursor.getInt(cursor.getColumnIndex("id"));
                 String name=cursor.getString(cursor.getColumnIndex("name"));
                 String size=cursor.getString(cursor.getColumnIndex("size"));
                 String quantity=cursor.getString(cursor.getColumnIndex("quantity"));
                String price =cursor.getString(cursor.getColumnIndex("price"));

                 food_request food_request=new food_request(id,name,size,quantity,price);
                 food_requests.add(food_request);

            } while (cursor.moveToNext());
        }

        return food_requests;

    }


   //_______________________________________________________DELETE ALL REQUESTS_______________________________________//
    public void delet_all_request(int size)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from food ",null);
   if(cursor.moveToFirst())
   {

      for(int i=0;i<=size;i++) {
          db.delete("food", "id=?", new String[]{String.valueOf(i)});

      }


   }



    }

    //_______________________________________________________DELETE A REQUEST _________________________________________//

    public void delet_request(int  id)
    {

          SQLiteDatabase db=this.getWritableDatabase();
          Cursor cursor=db.rawQuery("select *from food",null);
          if(cursor.moveToFirst()) {
              db.delete("food", "id=?", new String[]{String.valueOf(id)});
          }

    }

    public void clear_database()
    {
        SQLiteDatabase db=this.getWritableDatabase();
            //db.execSQL("select * from food",null);

                      db.delete("food",null,null);


    }

}
