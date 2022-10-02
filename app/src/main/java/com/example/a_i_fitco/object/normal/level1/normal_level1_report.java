package com.example.a_i_fitco.object.normal.level1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a_i_fitco.DBHelper;
import com.example.a_i_fitco.MainMenuActivity;
import com.example.a_i_fitco.ProgressList;
import com.example.a_i_fitco.R;
import com.example.a_i_fitco.mainlogin;
import com.example.a_i_fitco.stopwatcher;

public class normal_level1_report extends AppCompatActivity {

    String username_value = mainlogin.getUsername_value();
    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_level1_report);
        TextView durasi = findViewById(R.id.notextView5);
        TextView kalori = findViewById(R.id.notextView7);
        AlertDialog.Builder alert = new AlertDialog.Builder(normal_level1_report.this);
        long getDifference = stopwatcher.getDifference();
        long getDifferencew1 = stopwatcher.getDifferencew1();
        long getDifferencew2 = stopwatcher.getDifferencew2();
        long getDifferencew3 = stopwatcher.getDifferencew3();
        long getDifferencew4 = stopwatcher.getDifferencew4();
        long getDifferencew5 = stopwatcher.getDifferencew5();

        long getMinute = (getDifference/1000)/60;
        long getMinutew1 = (getDifferencew1/1000)/60;
        long getMinutew2 = (getDifferencew2/1000)/60;
        long getMinutew3 = (getDifferencew3/1000)/60;
        long getMinutew4 = (getDifferencew4/1000)/60;
        long getMinutew5 = (getDifferencew5/1000)/60;
        long getSecond = (int)((getDifference / 1000) % 60);



        double burpeesMET = 8*3.5;
        double sitUpMET = 8*3.5;
        double pushupMET = 3.8*3.5;

        String weight_value = DB.checkweight(username_value);
        float weighto = Float.parseFloat(weight_value);
        double calories = (((getMinutew1+getMinutew2)*sitUpMET)+((getMinutew3+getMinutew4)*pushupMET)+(getMinutew5*burpeesMET))*(weighto/200);
        String s=String.valueOf(getMinute);
        String m=String.valueOf(getSecond);
        String e=String.valueOf(calories);
        String id_value = DB.checkid(username_value);
        String workout = "10x2 Sit Ups, 10x2 Push Ups & 10x1 Burpees";
        float kalorion = (float) calories;
        String durasion = s+" minutes "+m+" seconds";
        durasi.setText(s+" minutes "+m+" seconds");
        kalori.setText(e+" kcal ");

        DB.insertDataProgress(id_value,workout,durasion,kalorion);

        Button btn = (Button)findViewById(R.id.noplist);
        Button btn1 = (Button)findViewById(R.id.nol1_finish);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), ProgressList.class);
                view.getContext().startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                alert.setTitle("Difficulty");
                alert.setTitle("Do you want to do harder exercise next time?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.updatestatus(username_value, "6");
                        startActivity(new Intent(normal_level1_report.this, MainMenuActivity.class));

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.updatestatus(username_value, "5");
                        startActivity(new Intent(normal_level1_report.this, MainMenuActivity.class));

                    }
                });
                alert.show();
            }
        });
    }
}