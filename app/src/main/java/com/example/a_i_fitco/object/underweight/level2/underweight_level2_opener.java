package com.example.a_i_fitco.object.underweight.level2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a_i_fitco.R;
import com.example.a_i_fitco.object.underweight.level1.first.first_un;
import com.example.a_i_fitco.object.underweight.level2.first.first2_un;
import com.example.a_i_fitco.stopwatcher;

public class underweight_level2_opener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underweight_level2_opener);

        Button proceed = findViewById(R.id.uw_proceed);

        proceed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                stopwatcher.swstart();
                Intent intent = new Intent(view.getContext(), first2_un.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}