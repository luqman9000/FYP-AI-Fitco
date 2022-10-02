package com.example.a_i_fitco.object.overweight.level2.fourth.classification;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.WorkerThread;

import com.example.a_i_fitco.object.overweight.level1.first.o_l1_first_cdtimer;
import com.example.a_i_fitco.object.overweight.level1.fourth.o_l1_fourth_cdtimer;
import com.example.a_i_fitco.object.overweight.level2.fourth.o_l2_fourth_cdtimer;
import com.example.a_i_fitco.stopwatcher;
import com.example.a_i_fitco.vision.posedetector.classification.ClassificationResult;
import com.example.a_i_fitco.vision.posedetector.classification.EMASmoothing;
import com.example.a_i_fitco.vision.posedetector.classification.PoseClassifier;
import com.example.a_i_fitco.vision.posedetector.classification.PoseSample;
import com.example.a_i_fitco.vision.posedetector.classification.RepetitionCounter;
import com.google.common.base.Preconditions;
import com.google.mlkit.vision.pose.Pose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class fourth2classifier {
    //timer tmClass=new timer();
    private static final String TAG = "PoseClassifierProcessor";
    private static final String POSE_SAMPLES_FILE = "pose/burpees_poses_data.csv";
    //String getTimerStop = stopwatcher.getMinitStop();
    public int repsAfter;
    // Specify classes for which we want rep counting.
    // These are the labels in the given {@code POSE_SAMPLES_FILE}. You can set your own class labels
    // for your pose samples.
    private static final String SIT_CLASS = "burpees_up";
    private static final String[] POSE_CLASSES = {
            SIT_CLASS
    };

    private final boolean isStreamMode;

    private EMASmoothing emaSmoothing;
    public List<RepetitionCounter> repCounters;
    private PoseClassifier poseClassifier;
    private String lastRepResult;




    @WorkerThread
    public fourth2classifier(Context context, boolean isStreamMode) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper());
        this.isStreamMode = isStreamMode;
        if (isStreamMode) {
            emaSmoothing = new EMASmoothing();
            repCounters = new ArrayList<>();
            lastRepResult = "";
        }
        loadPoseSamples(context);

    }

    private void loadPoseSamples(Context context) {
        List<PoseSample> poseSamples = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(POSE_SAMPLES_FILE)));
            String csvLine = reader.readLine();
            while (csvLine != null) {
                // If line is not a valid {@link PoseSample}, we'll get null and skip adding to the list.
                PoseSample poseSample = PoseSample.getPoseSample(csvLine, ",");
                if (poseSample != null) {
                    poseSamples.add(poseSample);
                }
                csvLine = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error when loading pose samples.\n" + e);
        }
        poseClassifier = new PoseClassifier(poseSamples);
        if (isStreamMode) {
            for (String className : POSE_CLASSES) {
                repCounters.add(new RepetitionCounter(className));
            }
        }
    }

    /**
     * Given a new {@link Pose} input, returns a list of formatted {@link String}s with Pose
     * classification results.
     *
     * <p>Currently it returns up to 2 strings as following:
     * 0: PoseClass : X reps
     * 1: PoseClass : [0.0-1.0] confidence
     */


    @WorkerThread
    public List<String> getPoseResult(Pose pose, Context context) {

        Intent intent = new Intent(context, o_l2_fourth_cdtimer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper());
        List<String> result = new ArrayList<>();
        ClassificationResult classification = poseClassifier.classify(pose);
        //timer tmClass=new timer();


        // Update {@link RepetitionCounter}s if {@code isStreamMode}.
        if (isStreamMode) {
            // Feed pose to smoothing even if no pose found.
            classification = emaSmoothing.getSmoothedResult(classification);

            // Return early without updating repCounter if no pose found.
            if (pose.getAllPoseLandmarks().isEmpty()) {
                result.add(lastRepResult);
                return result;
            }

            for (RepetitionCounter repCounter : repCounters) {
                int repsBefore = repCounter.getNumRepeats();
                repsAfter = repCounter.addClassificationResult(classification);
                if (repsAfter > repsBefore) {
                    // Play a fun beep when rep counter updates.
                    ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
                    tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                    lastRepResult = String.format(
                            Locale.US, "%s : %d reps", repCounter.getClassName(), repsAfter);
                    break;
                }

                if (repsAfter == 10){
                    //simpan nilai timer dekat sini dulu
                    //swstopw2();
                    //tmClass.stopTimer();
                    stopwatcher.swstopw4();
                    context.startActivity(intent);
                }

            }
            result.add(lastRepResult);
        }



        // Add maxConfidence class of current frame to result if pose is found.
        if (!pose.getAllPoseLandmarks().isEmpty()) {
            String maxConfidenceClassResult = "Pose Detected! Do 10 Burpees!";
            result.add(maxConfidenceClassResult);
        }

        return result;
    }
}
