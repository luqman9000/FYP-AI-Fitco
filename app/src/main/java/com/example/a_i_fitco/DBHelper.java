package com.example.a_i_fitco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";
    public  DBHelper(Context context){
        super(context, "Login.db", null, 1);
    }
    private static String myid;
    public static String getID_value(){

        return myid;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table users(id integer primary key autoincrement, username TEXT, password TEXT, email TEXT, status TEXT, weight FLOAT)");
        db.execSQL("create table progress(date DATETIME DEFAULT CURRENT_TIMESTAMP, id TEXT, workout TEXT, duration TEXT, calories REAL )");
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

    public void updatestatus(String username, String status){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        db.update("users", values, "username=?", new String[]{username});
        db.close();

    }

    public void updateweight(String username, float weight){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("weight", weight);
        db.update("users", values, "username=?", new String[]{username});
        db.close();

    }

    public Cursor checkStatus(String username){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT status from users where username=?", new String[] {username});
        return cursor;
    }

    public Boolean insertDataProgress(String id, String workout, String duration, Float calories){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("date", strDate);
        values.put("id", id);
        values.put("workout", workout);
        values.put("duration", duration);
        values.put("calories", calories);

        long result = db.insert("progress", null, values);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor checkProgress(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from progress where id=?", new String[] {id},null);
        return cursor;
    }

    //fetch a data
    public String checkid(String username){
        String bankbalresult;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id from users where username=?", new String[] {username});
        {
            cursor.moveToFirst();
            do {
                bankbalresult = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return bankbalresult;
    }

    //fetch weight data
    public String checkweight(String username){
        String bankbalresult;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT weight from users where username=?", new String[] {username});
        {
            cursor.moveToFirst();
            do {
                bankbalresult = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return bankbalresult;
    }




}
