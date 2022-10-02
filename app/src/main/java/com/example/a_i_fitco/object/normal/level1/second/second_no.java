package com.example.a_i_fitco.object.normal.level1.second;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.normal.level1.fourth.fourthnoprocessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class second_no extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new secondnoprocessor(
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
