package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a_i_fitco.object.ppushuponly.pushuponly;

public class OverweightActivity extends AppCompatActivity {
    Button proceed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overweight);
        proceed3=findViewById(R.id.overweight_proceed);
        proceed3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), pushuponly.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}