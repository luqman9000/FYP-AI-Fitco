package com.example.a_i_fitco.object.overweight.level1.first;
import com.example.a_i_fitco.camera_activity;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

public class first_ov extends camera_activity
{
    @Override
protected void setProcessor() {
    AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
            .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
            .build();
    cameraSource.setMachineLearningFrameProcessor(new firstprocessor(
                    this,
                    options,
                    false,
                    false,
                    false,
                    true,
                    true
            )
    );
}
}
