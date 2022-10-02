package com.example.a_i_fitco;

import android.os.SystemClock;
import android.util.Log;



public class stopwatcher {
    private static long startTime = 0;
    private static long startTimew2 = 0;
    private static long startTimew3 = 0;
    private static long startTimew4 = 0;
    private static long startTimew5 = 0;

    //overall workout duration
    private static long difference;
    public static long getDifference(){

        return difference;
    }

    // get workout 1 duration
    private static long differencew1;
    public static long getDifferencew1(){

        return differencew1;
    }

    //get workout 2 duration
    private static long differencew2;
    public static long getDifferencew2(){

        return differencew2;
    }

    //get workout 3 duration
    private static long differencew3;
    public static long getDifferencew3(){

        return differencew3;
    }

    //get workout 4 duration
    private static long differencew4;
    public static long getDifferencew4(){

        return differencew4;
    }

    //get workout 5 duration
    private static long differencew5;
    public static long getDifferencew5(){

        return differencew5;
    }

    //get workout 6 duration
    private static long differencew6;
    public static long getDifferencew6(){

        return differencew6;
    }

    //start recording duration for all workout and workout 1
    public static void swstart(){
        startTime = SystemClock.elapsedRealtime();
    }

    public static void swstop(){
        difference = SystemClock.elapsedRealtime() - startTime;
        Log.d("Overall workout = ", String.valueOf(difference));

    }

    public static void swstopw1(){
        differencew1 = SystemClock.elapsedRealtime() - startTime;
        Log.d("workout duration 1 = ", String.valueOf(difference));


    }

    //start recording duration for workout 2
    public static void swstart2(){startTimew2 = SystemClock.elapsedRealtime();
    }

    //start recording duration for workout 2
    public static void swstopw2(){
        differencew2 = SystemClock.elapsedRealtime() - startTimew2;
        Log.d("workout duration 2 = ", String.valueOf(differencew2));
    }

    //start recording duration for workout 3
    public static void swstart3(){startTimew3 = SystemClock.elapsedRealtime();
    }

    //start recording duration for workout 3
    public static void swstopw3(){
        differencew3 = SystemClock.elapsedRealtime() - startTimew3;
        Log.d("workout duration 3 = ", String.valueOf(differencew3));
    }

    //start recording duration for workout 4
    public static void swstart4(){startTimew4 = SystemClock.elapsedRealtime();
    }

    //start recording duration for workout 4
    public static void swstopw4(){
        differencew4 = SystemClock.elapsedRealtime() - startTimew4;
        Log.d("workout duration 4 = ", String.valueOf(differencew4));
    }

    //start recording duration for workout 5
    public static void swstart5(){startTimew5 = SystemClock.elapsedRealtime();
    }

    //start recording duration for workout 5
    public static void swstopw5(){
        differencew5 = SystemClock.elapsedRealtime() - startTimew5;
        Log.d("workout duration 5 = ", String.valueOf(differencew5));
    }
}
