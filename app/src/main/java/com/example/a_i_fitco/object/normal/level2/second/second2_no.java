package com.example.a_i_fitco.object.normal.level2.second;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.normal.level1.fifth.fifthnoprocessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class second2_no extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new second2noprcosessor(
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
