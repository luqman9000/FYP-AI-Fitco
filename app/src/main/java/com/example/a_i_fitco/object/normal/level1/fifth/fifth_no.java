package com.example.a_i_fitco.object.normal.level1.fifth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.a_i_fitco.MainMenuActivity;
import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.normal.level1.first.n_l1_first_cdtimer;
import com.example.a_i_fitco.object.normal.level1.second.second_no;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class fifth_no extends camera_activity {


    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new fifthnoprocessor(
                        this,
                        options,
                        true,
                        false,
                        false,
                        true,
                        true
                )
        );

    }

}
