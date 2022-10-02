package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ProgressList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> date,workout,duration,calories;
    DBHelper DB;
    MyAdapter adapter;

    String username_value = mainlogin.getUsername_value();
    String id_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_list);
        DB = new DBHelper(this);
        date = new ArrayList<>();
        workout = new ArrayList<>();
        duration = new ArrayList<>();
        calories = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, date, workout, duration, calories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        id_value = DB.checkid(username_value);
        Cursor cursor = DB.checkProgress(id_value);
        if(cursor.getCount()==0){
            Toast.makeText(ProgressList.this,"No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(cursor.moveToNext()){
                date.add(cursor.getString(0));
                workout.add(cursor.getString(4));
                duration.add(cursor.getString(3));
                calories.add(cursor.getString(2));
            }
        }
    }
}