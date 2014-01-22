package uk.co.cogitolearning.propertyanimations_example;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyAnimation07 extends Activity
{

  private ObjectAnimator anim;
  private TextView isStartedText;
  private TextView isRunningText;
  private TextView isPausedText;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations07);

    isStartedText = (TextView) findViewById(R.id.status_is_started);
    isRunningText = (TextView) findViewById(R.id.status_is_running);
    isPausedText = (TextView) findViewById(R.id.status_is_paused);

    ImageView someImage = (ImageView) findViewById(R.id.some_image);

    anim = ObjectAnimator.ofFloat(someImage, "rotation", 0, 360);
    anim.setDuration(1000);
    anim.setRepeatCount(5);
    anim.setRepeatMode(ObjectAnimator.RESTART);
    setStatusTexts();
  }

  @SuppressLint("NewApi")
  public void setStatusTexts()
  {
    if (android.os.Build.VERSION.SDK_INT >= 14)
      isStartedText.setText("isStarted = " + anim.isStarted());

    isRunningText.setText("isRunning = " + anim.isRunning());

    if (android.os.Build.VERSION.SDK_INT >= 19)
      isPausedText.setText("isPaused = " + anim.isPaused());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void startAnimation(View view)
  {
    anim.start();
    setStatusTexts();
  }

  public void endAnimation(View view)
  {
    anim.end();
    setStatusTexts();
  }

  public void cancelAnimation(View view)
  {
    anim.cancel();
    setStatusTexts();
  }

  @SuppressLint("NewApi")
  public void pauseAnimation(View view)
  {
    if (android.os.Build.VERSION.SDK_INT >= 19)
      anim.pause();
    setStatusTexts();
  }

  @SuppressLint("NewApi")
  public void resumeAnimation(View view)
  {
    if (android.os.Build.VERSION.SDK_INT >= 19)
      anim.resume();
    setStatusTexts();
  }

}
