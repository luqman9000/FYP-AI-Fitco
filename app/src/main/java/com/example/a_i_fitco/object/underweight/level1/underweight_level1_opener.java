package com.example.a_i_fitco.object.underweight.level1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a_i_fitco.R;
import com.example.a_i_fitco.object.overweight.level1.first.first_ov;
import com.example.a_i_fitco.object.underweight.level1.first.first_un;
import com.example.a_i_fitco.stopwatcher;

public class underweight_level1_opener extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_underweight_level1_opener);
            Button proceed = findViewById(R.id.uw_proceed);

            proceed.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    stopwatcher.swstart();
                    Intent intent = new Intent(view.getContext(), first_un.class);
                    view.getContext().startActivity(intent);
                }
            });
        }

}