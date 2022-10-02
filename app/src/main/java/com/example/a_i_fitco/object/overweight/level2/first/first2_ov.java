package com.example.a_i_fitco.object.overweight.level2.first;
import com.example.a_i_fitco.camera_activity;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class first2_ov extends camera_activity {
    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new first2processor(
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
