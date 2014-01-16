package uk.co.cogitolearning.propertyanimations_example;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.RectEvaluator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PropertyAnimation06 extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.property_animations06);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @SuppressLint("NewApi")
  public void startRectAnimation(View view)
  {    
    View someImage = findViewById(R.id.some_image);
    Rect local = new Rect();
    someImage.getLocalVisibleRect(local);
    Rect from = new Rect(local);
    Rect to = new Rect(local);
    
    from.right = from.left + local.width()/4;
    from.bottom = from.top + local.height()/2;
    
    to.left = to.right - local.width()/2;
    to.top = to.bottom - local.height()/4;
    
    if (android.os.Build.VERSION.SDK_INT >= 18)
    {
      ObjectAnimator anim = ObjectAnimator.ofObject(someImage, "clipBounds", new RectEvaluator(), from, to);
      anim.setDuration(2000);
      anim.start();
    }
    
  }
  
  public void startRgbAnimation(View view)
  {    
    View square = findViewById(R.id.some_view);
    
    ObjectAnimator anim = ObjectAnimator.ofObject(square, "backgroundColor", new ArgbEvaluator(), Color.RED, Color.BLUE);
    anim.setDuration(2000);
    anim.start();
    
  }

  public void startHsvAnimation(View view)
  {
    View square = findViewById(R.id.some_view);
    
    ObjectAnimator anim = ObjectAnimator.ofObject(square, "backgroundColor", new HsvEvaluator(), Color.RED, Color.BLUE);
    anim.setDuration(2000);
    anim.start();
  }

  public void startSkewAnimation(View view)
  {
    ImageView image = (ImageView)findViewById(R.id.some_image);

    float scale = (float)image.getHeight()/(float)image.getDrawable().getIntrinsicHeight();
    Matrix from = new Matrix();
    from.setScale(scale, scale);
    from.postSkew(-0.5f, 0.0f);
    Matrix to = new Matrix(image.getMatrix());
    to.setScale(scale, scale);
    to.postSkew(0.5f, 0.0f);
    
    image.setScaleType(ScaleType.MATRIX);
    Matrix start = new Matrix();
    start.setScale(scale, scale);
    image.setImageMatrix(start);
    
    ObjectAnimator anim = ObjectAnimator.ofObject(image, "imageMatrix", new MatrixEvaluator(), from, to);
    anim.setDuration(500);
    anim.setRepeatCount(5);
    anim.setRepeatMode(ObjectAnimator.REVERSE);
    anim.start();
    
  }
  
}
