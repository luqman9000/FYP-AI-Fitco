package com.example.a_i_fitco.object.psquatonly;

import android.os.Bundle;

import com.example.a_i_fitco.camera_activity;

import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class squatonly extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new SposeDetectorProcessor(
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
