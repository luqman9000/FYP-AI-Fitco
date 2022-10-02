package com.example.a_i_fitco.object.underweight.level1.fifth;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.underweight.level1.third.thirdunprocessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class fifth_un extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new fifthunprocessor(
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
