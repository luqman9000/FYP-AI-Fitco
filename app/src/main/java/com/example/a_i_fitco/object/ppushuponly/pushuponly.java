package com.example.a_i_fitco.object.ppushuponly;

import android.os.Bundle;

import com.example.a_i_fitco.camera_activity;
import com.example.a_i_fitco.object.ppushuponly.PposeDetectorProcessor;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;
import com.example.a_i_fitco.object.ppushuponly.classification.PposeClassifierProcessor;




public class pushuponly extends camera_activity {

    @Override
    protected void setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        cameraSource.setMachineLearningFrameProcessor(new PposeDetectorProcessor(
                        this,
                        options,
                        true,
                        false,
                        false,
                        true,
                        true
                )
        );
        //if (newrep == 5)
        //{
        //    pptoast.showToast(this, "disguided toast");
        //}
    }

}
