package com.example.a_i_fitco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a_i_fitco.object.normal.level1.normal_level1_opener;
import com.example.a_i_fitco.object.overweight.level1.overweight_level1_opener;
import com.example.a_i_fitco.object.underweight.level1.underweight_level1_opener;

import java.text.DecimalFormat;



public class BMIActivity extends AppCompatActivity {



    String username_value = mainlogin.getUsername_value();
    timer tmClass=new timer();//declare timer from class timer
    stopwatcher stopwatcher = new stopwatcher();
    DBHelper DB = new DBHelper(this);
    private static double dweight;
    public static Double getWeight(){

        return dweight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        myButtonListenerMethod();
    }
public void myButtonListenerMethod() {
    //variable for every widget
        Button btn = (Button)findViewById(R.id.button2);

        btn.setOnClickListener(view -> {
            EditText weight = (EditText) findViewById(R.id.editTextNumber3);
            String weightStr = weight.getText().toString();
            dweight = Double.parseDouble(weightStr);
            EditText height = (EditText) findViewById(R.id.editTextNumber5);
            String heightStr = height.getText().toString();
            double dwayne = Double.parseDouble(weightStr);
            double dheight = Double.parseDouble(heightStr);
            double heightM = dheight/100;

            double BMI = (dweight)/((heightM)*(heightM));
            DecimalFormat df = new DecimalFormat("#.#");

            //TextView BMI_result = findViewById(R.id.textBMI);
            //TextView BMI_weight = findViewById(R.id.textWeight);
            //BMI_result.setText(Double.toString(BMI_p));
            String BMI_cat;
            if(BMI<18.5){
                DB.updateweight(username_value, (float) dwayne);
                Intent intent1 = new Intent(BMIActivity.this, underweight_level1_opener.class);
                startActivity(intent1);
                tmClass.startTimer();//start timer
                //BMI_cat = "Underweight";
            }else if(BMI>=18.5 && BMI <25.0){
                DB.updateweight(username_value, (float) dwayne);
                Intent intent2 = new Intent(BMIActivity.this, normal_level1_opener.class);
                startActivity(intent2);
                //BMI_cat = "Healthy";
            }
            else{
                DB.updateweight(username_value, (float) dwayne);
                Intent intent3 = new Intent(BMIActivity.this, overweight_level1_opener.class);
                startActivity(intent3);

                tmClass.startTimer();
                //BMI_cat = "Overweight";
            }
            //set result into bmi weight textview
            //BMI_weight.setText(BMI_cat);

        });

    }



}

