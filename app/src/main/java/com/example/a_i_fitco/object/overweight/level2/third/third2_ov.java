package com.example.a_i_fitco.object.overweight.level2.third;

import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;
import com.example.a_i_fitco.camera_activity;

public class third2_ov extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new third2processor(
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
