package uk.co.cogitolearning.propertyanimations_example;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PropertyAnimation02 extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations01);
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
    View image = findViewById(R.id.some_image);
    ObjectAnimator anim = ObjectAnimator.ofFloat(image, "rotationY", 0.0f, 360.0f);
    anim.setDuration(300);
    anim.start();
  }

}
