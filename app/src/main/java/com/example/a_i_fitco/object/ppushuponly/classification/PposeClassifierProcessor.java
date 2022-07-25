/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a_i_fitco.object.ppushuponly.classification;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.WorkerThread;

import com.example.a_i_fitco.BMIActivity;
import com.example.a_i_fitco.pcountdowntimer;
import com.example.a_i_fitco.vision.posedetector.classification.ClassificationResult;
import com.example.a_i_fitco.vision.posedetector.classification.EMASmoothing;
import com.example.a_i_fitco.vision.posedetector.classification.PoseClassifier;
import com.example.a_i_fitco.vision.posedetector.classification.PoseSample;
import com.example.a_i_fitco.vision.posedetector.classification.RepetitionCounter;
import com.google.common.base.Preconditions;
import com.google.mlkit.vision.pose.Pose;
import android.content.Intent;

import static xdroid.toaster.Toaster.toast;
import static xdroid.toaster.Toaster.toastLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



/**
 * Accepts a stream of {@link Pose} for classification and Rep counting.
 */
public class PposeClassifierProcessor{
  Context mContext;
  private static final String TAG = "PoseClassifierProcessor";
  private static final String POSE_SAMPLES_FILE = "pose/pushup_pose_samples.csv";

  public int repsAfter;
  // Specify classes for which we want rep counting.
  // These are the labels in the given {@code POSE_SAMPLES_FILE}. You can set your own class labels
  // for your pose samples.
  private static final String PUSHUPS_CLASS = "pushups_down";
  private static final String[] POSE_CLASSES = {
    PUSHUPS_CLASS
  };

  private final boolean isStreamMode;

  private EMASmoothing emaSmoothing;
  public List<RepetitionCounter> repCounters;
  private PoseClassifier poseClassifier;
  private String lastRepResult;




  @WorkerThread
  public PposeClassifierProcessor(Context context, boolean isStreamMode) {
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
    Intent intent = new Intent(context, pcountdowntimer.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper());
    List<String> result = new ArrayList<>();
    ClassificationResult classification = poseClassifier.classify(pose);

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

        if (repsAfter == 3){

          //Toast.makeText(context, "You've reached 3 reps!!", Toast.LENGTH_SHORT).show();
          //break;
          context.startActivity(intent);
        }

      }
      result.add(lastRepResult);
    }



    // Add maxConfidence class of current frame to result if pose is found.
    if (!pose.getAllPoseLandmarks().isEmpty()) {
      String maxConfidenceClass = classification.getMaxConfidenceClass();
      String maxConfidenceClassResult = String.format(
          Locale.US,
          "%s : %.2f confidence",
          maxConfidenceClass,
          classification.getClassConfidence(maxConfidenceClass)
              / poseClassifier.confidenceRange());
      result.add(maxConfidenceClassResult);
    }

    return result;
  }

}
