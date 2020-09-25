package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class history_database extends SQLiteOpenHelper {
    private static final String DB_name="history";
    private static final String DB_table="history_table";
    private static final int db_version=1;
    public history_database(@Nullable Context context) {
        super(context, DB_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL("create table history_table (id Integer primary key ,phone varchar (30),addresse varchar (30),total varchar (30),date varchar(20),time varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  sqLiteDatabase.execSQL("DROP Table history_table ");
  onCreate(sqLiteDatabase);
    }


    public void add_history(historique historique)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
         values.put("phone",historique.getPhone());
         values.put("addresse",historique.getAddresse());
         values.put("total",historique.getTotal());
         values.put("date",historique.getDate());
        values.put("time",historique.getTime());
         db.insert(DB_table,null,values);
    }

    public ArrayList<historique2> get_all_history() {

         SQLiteDatabase db=this.getReadableDatabase();
         ArrayList<historique2>historiques=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from history_table",null);
        if (cursor.moveToFirst())
        {
            do{

                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String phone=cursor.getString(cursor.getColumnIndex("phone"));
                String addresse=cursor.getString(cursor.getColumnIndex("addresse"));
                String total=cursor.getString(cursor.getColumnIndex("total"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String time=cursor.getString(cursor.getColumnIndex("time"));

                historique2 historique2=new historique2(id,phone,addresse,total,date,time);
                historiques.add(historique2);


            }while(cursor.moveToNext());
        }


         return historiques;

    }
}
