package uk.co.cogitolearning.propertyanimations_example;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
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
    
    ObjectAnimator anim1 = ObjectAnimator.ofFloat(someImage, "x", 20.0f*scale, 220.0f*scale);
    ObjectAnimator anim2 = ObjectAnimator.ofFloat(someImage, "y", 20.0f*scale, 220.0f*scale);
    ObjectAnimator anim3 = ObjectAnimator.ofFloat(someImage, "x", 220.0f*scale, 20.0f*scale);
    ObjectAnimator anim4 = ObjectAnimator.ofFloat(someImage, "y", 220.0f*scale, 20.0f*scale);
    
    // Fine grained control over all animations inside the set
    anim1.setDuration(500);
    anim2.setDuration(500);
    anim3.setDuration(1000);
    anim4.setDuration(1000);

    anim1.setInterpolator(new AccelerateInterpolator());
    anim2.setInterpolator(new DecelerateInterpolator());
    anim3.setInterpolator(new LinearInterpolator());
    anim4.setInterpolator(new AccelerateDecelerateInterpolator());
    
    AnimatorSet animSet = new AnimatorSet();
    
    //  Long version to set up animation set
//    animSet.play(anim1).before(anim2);
//    animSet.play(anim3).after(anim2);
//    animSet.play(anim3).with(anim4);
//    animSet.play(anim1).after(500);

    // Abbreviated version using chained calls to Builder methods
    animSet.play(anim1).before(anim2).after(500);
    animSet.play(anim3).after(anim2).with(anim4);
    
    // Global control. Uncomment if not using fine grained control
//    animSet.setDuration(1000);
//    animSet.setInterpolator(new AccelerateDecelerateInterpolator());
    animSet.start();
  }

}
