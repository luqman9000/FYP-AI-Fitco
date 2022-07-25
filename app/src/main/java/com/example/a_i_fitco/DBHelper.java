package com.example.a_i_fitco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";
    public  DBHelper(Context context){
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table users(username TEXT,password TEXT, email TEXT, status TEXT)");
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists users");
    }
// insert new data username,password,email into database
    public Boolean insertData(String username, String password, String email,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("email", email);
        values.put("status", status);

        long result = db.insert("users", null, values);
        if(result==-1) return false;
        else
            return true;
    }


    public boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


     public boolean checkusernameandpassword(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
         Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
         if(cursor.getCount()>0)
             return true;
         else
             return false;
     }

    public boolean updatestatus(String username, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET status=? where username=?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor checkStatus(String username){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT status from users where username=?", new String[] {username});
        return cursor;
    }





}
