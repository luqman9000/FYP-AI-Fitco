package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a_i_fitco.object.ppushuponly.pushuponly;

public class UnderweightActivity extends AppCompatActivity {
    Button proceed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underweight);
        proceed2=findViewById(R.id.underweight_proceed);

        proceed2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), pushuponly.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}