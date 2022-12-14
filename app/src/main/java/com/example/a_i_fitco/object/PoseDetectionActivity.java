package com.example.a_i_fitco.object;

import android.os.Bundle;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.vision.posedetector.PoseDetectorProcessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;


public class PoseDetectionActivity extends camera_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new PoseDetectorProcessor(
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
