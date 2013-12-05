package uk.co.cogitolearning.propertyanimations_example;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PropertyAnimation04 extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations04);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void flipOnVertical(View view)
  {
    View policebox = findViewById(R.id.policebox);
    View policeboxlight = findViewById(R.id.policeboxlight);
    ObjectAnimator anim1 = ObjectAnimator.ofFloat(policebox, "alpha", 1.0f, 1.0f, 0.25f, 0.75f, 0.15f, 0.5f, 0.0f);
    ObjectAnimator anim2 = ObjectAnimator.ofFloat(policeboxlight, "alpha", 0.0f, 1.0f, 0.0f, 0.75f, 0.0f, 0.5f, 0.0f);
    AnimatorSet animSet = new AnimatorSet();
    
    animSet.playTogether(anim1, anim2);
    
    animSet.setDuration(5000);
    animSet.setInterpolator(new LinearInterpolator());
    animSet.start();
  }

}
