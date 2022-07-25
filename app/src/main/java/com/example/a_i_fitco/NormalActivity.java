package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a_i_fitco.object.ppushuponly.pushuponly;

public class NormalActivity extends AppCompatActivity {
    Button proceed1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        proceed1=findViewById(R.id.normal_proceed);

        proceed1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), pushuponly.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}