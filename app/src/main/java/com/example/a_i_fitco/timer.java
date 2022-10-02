package com.example.a_i_fitco;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Timer;

public class timer
{
    private static Timer timer = new Timer();

    public static final int TIME_INTERVAL = 1000;
    private static int saat = 0;
    private static long minute = (saat /60) % 60;
    private static String minit = Long.toString(saat);
    public static String getMinit(){

        return minit;
    }

    public void startTimer() {

        Log.d("Constants", "Timer Started");
        timer.scheduleAtFixedRate(new java.util.TimerTask() {

            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                saat++;
                Log.d("Saat = ", String.valueOf(saat));
                //Performing my Operations

            }
        }, 0, TIME_INTERVAL);

    }
    public void stopTimer() {
        timer.cancel();
    }


}
