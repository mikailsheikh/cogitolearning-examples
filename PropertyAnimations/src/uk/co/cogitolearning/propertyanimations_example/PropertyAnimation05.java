package uk.co.cogitolearning.propertyanimations_example;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PropertyAnimation05 extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations05);
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
    float scale = getResources().getDisplayMetrics().density;
    
    View someImage = findViewById(R.id.some_image);
    
    ObjectAnimator anim1 = ObjectAnimator.ofFloat(someImage, "x", 20.0f*scale, 120.0f*scale);
    ObjectAnimator anim2 = ObjectAnimator.ofFloat(someImage, "y", 20.0f*scale, 120.0f*scale);
    ObjectAnimator anim3 = ObjectAnimator.ofFloat(someImage, "x", 120.0f*scale, 20.0f*scale);
    ObjectAnimator anim4 = ObjectAnimator.ofFloat(someImage, "y", 120.0f*scale, 20.0f*scale);
    
    AnimatorSet animSet = new AnimatorSet();
    
    //  Long version
//    animSet.play(anim1).before(anim2);
//    animSet.play(anim3).after(anim2);
//    animSet.play(anim3).with(anim4);
//    animSet.play(anim1).after(500);

    // Abbreviated version using chained calls to Builder methods
    animSet.play(anim1).before(anim2).after(500);
    animSet.play(anim3).after(anim2).with(anim4);
    
    animSet.setDuration(1000);
    animSet.setInterpolator(new LinearInterpolator());
    animSet.start();
  }

}
