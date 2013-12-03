package uk.co.cogitolearning.propertyanimations_example;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class PropertyAnimation01 extends Activity
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
    Animator anim = AnimatorInflater.loadAnimator(this, R.animator.flip_on_vertical);
    anim.setTarget(image);
    anim.start();
  }

}
