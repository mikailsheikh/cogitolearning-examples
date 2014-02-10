package uk.co.cogitolearning.propertyanimations_example;

import android.animation.TimeAnimator;
import android.animation.TimeAnimator.TimeListener;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
class FpsTimeListener implements TimeListener
{
  private double fps;
  private TextView textView;

  public FpsTimeListener(TextView textView)
  {
    this.textView = textView;
    this.fps = -1.0;
  }

  public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime)
  {

    double currentFps;
    if (deltaTime != 0)
      currentFps = 1000.0 / (double) deltaTime;
    else
      currentFps = 0.9 * fps;
    if (fps < 0.0)
      fps = currentFps;
    else
      fps = 0.9 * fps + 0.1 * currentFps;
    textView.setText(String.format("fps: %.2f", fps));
  }
}
