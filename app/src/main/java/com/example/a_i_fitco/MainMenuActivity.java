package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a_i_fitco.object.normal.level1.normal_level1_opener;
import com.example.a_i_fitco.object.normal.level2.normal_level2_opener;
import com.example.a_i_fitco.object.overweight.level1.overweight_level1_opener;
import com.example.a_i_fitco.object.overweight.level2.overweight_level2_opener;
import com.example.a_i_fitco.object.underweight.level1.underweight_level1_opener;
import com.example.a_i_fitco.object.underweight.level2.underweight_level2_opener;

public class MainMenuActivity extends AppCompatActivity {
    String username_value = mainlogin.getUsername_value();
    timer tmClass=new timer();
    DBHelper DB = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        TextView namanya = findViewById(R.id.textView13);
        Button btn = (Button)findViewById(R.id.button6);
        Button btn1 = (Button)findViewById(R.id.button7);
        Button btn2 = (Button)findViewById(R.id.button8);
        namanya.setText(username_value);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Cursor cursor = DB.checkStatus(username_value);
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainMenuActivity.this, "No data generate", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cursor.moveToFirst()) {
                    do {
                        String status = cursor.getString(0);
                        //status nak pergi activity pertama, kalau 0 maknanya first user pergi BMI activity
                        if(status.equals("0"))
                        {
                            Intent intent=new Intent(getApplicationContext(),BMIActivity.class);
                            startActivity(intent);
                        }
                        else if(status.equals("1"))
                        {
                            stopwatcher.swstart();
                            tmClass.startTimer();
                            Intent intent=new Intent(getApplicationContext(), overweight_level1_opener.class);
                            startActivity(intent);

                        }
                        else if(status.equals("2"))
                        {
                            Intent intent=new Intent(getApplicationContext(), overweight_level2_opener.class);
                            startActivity(intent);
                        }
                        else if(status.equals("3"))
                        {
                            Intent intent=new Intent(getApplicationContext(), underweight_level1_opener.class);
                            startActivity(intent);
                        }
                        else if(status.equals("4"))
                        {
                            Intent intent=new Intent(getApplicationContext(), underweight_level2_opener.class);
                            startActivity(intent);
                        }
                        else if(status.equals("5"))
                        {
                            Intent intent=new Intent(getApplicationContext(), normal_level1_opener.class);
                            startActivity(intent);
                        }
                        else if(status.equals("6"))
                        {
                            Intent intent=new Intent(getApplicationContext(), normal_level2_opener.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent=new Intent(getApplicationContext(),mainregister.class);
                            startActivity(intent);
                        }

                    } while (cursor.moveToNext());
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ProgressList.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), BMIActivity.class);
                startActivity(intent);

            }
        });
    }
}