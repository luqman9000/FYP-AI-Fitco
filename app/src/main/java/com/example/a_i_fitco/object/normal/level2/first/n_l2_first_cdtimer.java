package com.example.a_i_fitco.object.normal.level2.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a_i_fitco.MainMenuActivity;
import com.example.a_i_fitco.R;
import com.example.a_i_fitco.object.normal.level1.first.n_l1_first_cdtimer;
import com.example.a_i_fitco.object.normal.level1.fourth.fourth_no;
import com.example.a_i_fitco.object.normal.level2.second.second2_no;
import com.example.a_i_fitco.stopwatcher;

public class n_l2_first_cdtimer extends AppCompatActivity {

    public int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nl2_first_cdtimer);
        TextView textView = (TextView) findViewById(R.id.nl2ftextView);
        Button button = (Button) findViewById(R.id.nl2fpbutton);
        button.setVisibility(View.GONE);

        new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long milisUntilFinished) {
                textView.setText(String.valueOf(counter));
                counter++;
            }

            @Override
            public void onFinish() {
                button.setVisibility(View.VISIBLE);

            }
        }.start();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                stopwatcher.swstart2();
                Intent intent = new Intent(view.getContext(), second2_no.class);
                view.getContext().startActivity(intent);
            }
        });
        
    }

    public void onBackPressed() {


        AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Exercise");
        dialog.setMessage("You want to stop the exercise?(Your progress will not be saved) ");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(n_l2_first_cdtimer.this, MainMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                n_l2_first_cdtimer.this.startActivity(intent);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}