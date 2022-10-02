package com.example.a_i_fitco.object.overweight.level2.fifth;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.overweight.level2.first.first2processor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class fifth2_ov extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new fifth2processor(
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
