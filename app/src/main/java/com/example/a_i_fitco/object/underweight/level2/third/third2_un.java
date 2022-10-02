package com.example.a_i_fitco.object.underweight.level2.third;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.underweight.level2.second.second2unprocessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class third2_un extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new third2unprocessor(
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
