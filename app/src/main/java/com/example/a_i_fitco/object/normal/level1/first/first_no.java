package com.example.a_i_fitco.object.normal.level1.first;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.overweight.level1.first.firstprocessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class first_no extends camera_activity
{
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new firstnoprocessor(
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
