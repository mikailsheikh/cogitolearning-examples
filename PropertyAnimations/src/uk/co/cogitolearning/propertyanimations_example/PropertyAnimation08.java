package uk.co.cogitolearning.propertyanimations_example;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyAnimation08 extends Activity
{

  private ObjectAnimator anim;
  private TextView isStartedText;
  private TextView isRunningText;
  private TextView isPausedText;
  private TextView messageText;

  @Override
  @SuppressLint("NewApi")
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations08);

    isStartedText = (TextView) findViewById(R.id.status_is_started);
    isRunningText = (TextView) findViewById(R.id.status_is_running);
    isPausedText = (TextView) findViewById(R.id.status_is_paused);
    messageText = (TextView) findViewById(R.id.message_text);

    ImageView someImage = (ImageView) findViewById(R.id.some_image);

    anim = ObjectAnimator.ofFloat(someImage, "rotation", 0, 360);
    anim.setDuration(1000);
    anim.setStartDelay(1000);
    anim.setRepeatCount(5);
    anim.setRepeatMode(ObjectAnimator.RESTART);
    
    Animator.AnimatorListener animationListener = new Animator.AnimatorListener()
    {
      
      @Override
      public void onAnimationStart(Animator animation)
      {
       setStatusTexts();
       messageText.setText("started");
      }
      
      @Override
      public void onAnimationRepeat(Animator animation)
      {
        setStatusTexts();
        messageText.setText("repeating");
      }
      
      @Override
      public void onAnimationEnd(Animator animation)
      {
        setStatusTexts();
        messageText.setText(messageText.getText()+" -- ended");
      }
      
      @Override
      public void onAnimationCancel(Animator animation)
      {
        setStatusTexts();
        messageText.setText("cancelled");
      }
    };
    
    
    anim.addListener(animationListener);
    if (android.os.Build.VERSION.SDK_INT >= 19)
    {
     Animator.AnimatorPauseListener pauseListener = new Animator.AnimatorPauseListener()
     {
       
       @Override
       public void onAnimationResume(Animator animation)
       {
         setStatusTexts();
         messageText.setText("resumed");
       }
       
       @Override
       public void onAnimationPause(Animator animation)
       {
         setStatusTexts();
         messageText.setText("paused");
       }
     };
     anim.addPauseListener(pauseListener);
    }
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
  }

  public void endAnimation(View view)
  {
    anim.end();
  }

  public void cancelAnimation(View view)
  {
    anim.cancel();
  }

  @SuppressLint("NewApi")
  public void pauseAnimation(View view)
  {
    if (android.os.Build.VERSION.SDK_INT >= 19)
      anim.pause();
  }

  @SuppressLint("NewApi")
  public void resumeAnimation(View view)
  {
    if (android.os.Build.VERSION.SDK_INT >= 19)
      anim.resume();
  }

}
