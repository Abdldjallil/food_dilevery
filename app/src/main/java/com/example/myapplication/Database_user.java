package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database_user extends SQLiteOpenHelper {

    final private static String db_name="db_user";
    final private static  int db_ver=1;
    public Database_user(@Nullable Context context) {
        super(context, db_name, null, db_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL("create table users (id integer primary key ,phone varchar(20),password varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP Table users");
    onCreate(sqLiteDatabase);
    }

    public void add_user(User2 user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
         values.put("phone",user.getPhone());
         values.put("password",user.getPassword());
         db.insert("users",null,values);

    }


    public ArrayList<User3>get_user()
    {
         SQLiteDatabase db=this.getReadableDatabase();
         ArrayList<User3> users=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from users",null);
         if (cursor.moveToFirst())
         {
             do{
                   String phone=cursor.getString(cursor.getColumnIndex("phone"));
                   String password=cursor.getString(cursor.getColumnIndex("password"));
                   int id=cursor.getInt(cursor.getColumnIndex("id"));

                   User3 user = new User3(id,phone,password) ;
                   users.add(user);
             }while (cursor.moveToNext());

         }
        return users;

    }

    public void clear_database()
    {

        SQLiteDatabase db=this.getWritableDatabase();

             db.delete("users",null,null);



    }

}
