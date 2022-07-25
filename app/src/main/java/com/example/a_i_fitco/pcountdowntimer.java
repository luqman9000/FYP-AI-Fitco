package com.example.a_i_fitco;

import static xdroid.toaster.Toaster.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a_i_fitco.object.ppushuponly.pushuponly;
import com.example.a_i_fitco.object.psquatonly.squatonly;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class pcountdowntimer extends AppCompatActivity {

    //initialize variable
    public int counter;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcountdowntimer);
        //assign variable
        textView = (TextView) findViewById(R.id.textView3);
        button = (Button) findViewById(R.id.button4);
        button.setVisibility(View.GONE);

        new CountDownTimer(30000,1000) {
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
                Intent intent = new Intent(view.getContext(), squatonly.class);
                view.getContext().startActivity(intent);
            }
        });

    }
}