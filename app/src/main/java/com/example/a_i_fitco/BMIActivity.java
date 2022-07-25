package com.example.a_i_fitco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a_i_fitco.object.ppushuponly.pushuponly;

import java.text.DecimalFormat;



public class BMIActivity extends AppCompatActivity {


    Intent intent = getIntent();
    String username_value = mainlogin.getUsername_value();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        Toast.makeText(BMIActivity.this,"Welcome " + username_value, Toast.LENGTH_SHORT).show();
        myButtonListenerMethod();
    }
public void myButtonListenerMethod() {
    //variable for every widget
        Button btn = (Button)findViewById(R.id.button2);
        Button puv = (Button)findViewById(R.id.pushvision);

        btn.setOnClickListener(view -> {
            EditText weight = (EditText) findViewById(R.id.editTextNumber3);
            String weightStr = weight.getText().toString();
            double dweight = Double.parseDouble(weightStr);
            EditText height = (EditText) findViewById(R.id.editTextNumber5);
            String heightStr = height.getText().toString();
            double dheight = Double.parseDouble(heightStr);
            double heightM = dheight/100;

            double BMI = (dweight)/((heightM)*(heightM));
            DecimalFormat df = new DecimalFormat("#.#");
            double BMI_p = Double.parseDouble(df.format(BMI));

            //TextView BMI_result = findViewById(R.id.textBMI);
            //TextView BMI_weight = findViewById(R.id.textWeight);
            //BMI_result.setText(Double.toString(BMI_p));
            String BMI_cat;
            if(BMI<18.5){
                Intent intent1 = new Intent(BMIActivity.this, UnderweightActivity.class);
                startActivity(intent1);
                BMI_cat = "Underweight";
            }else if(BMI>=18.5 && BMI <25.0){
                Intent intent2 = new Intent(BMIActivity.this, NormalActivity.class);
                startActivity(intent2);
                BMI_cat = "Healthy";
            }
            else{
                Intent intent3 = new Intent(BMIActivity.this, OverweightActivity.class);
                startActivity(intent3);
                BMI_cat = "Overweight";
            }
            //set result into bmi weight textview
            //BMI_weight.setText(BMI_cat);

        });

    puv.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Intent intent = new Intent(view.getContext(), pushuponly.class);
            view.getContext().startActivity(intent);
        }
    });

    }



}

