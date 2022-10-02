package com.example.a_i_fitco.object.overweight.level1.fourth;
import com.example.a_i_fitco.camera_activity;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class fourth_ov extends camera_activity{
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new fourthprocessor(
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
