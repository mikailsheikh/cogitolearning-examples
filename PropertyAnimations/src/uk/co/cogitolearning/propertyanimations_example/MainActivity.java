package uk.co.cogitolearning.propertyanimations_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void goExample1(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation01.class);
    startActivity(intent);
  }

  public void goExample2(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation02.class);
    startActivity(intent);
  }
  
  public void goExample3(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation03.class);
    startActivity(intent);
  }

  public void goExample4(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation04.class);
    startActivity(intent);
  }

  public void goExample5(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation05.class);
    startActivity(intent);
  }
  
  public void goExample6(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation06.class);
    startActivity(intent);
  }
  
  public void goExample7(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation07.class);
    startActivity(intent);
  }
  
  public void goExample8(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation08.class);
    startActivity(intent);
  }
  
  public void goExample9(View view)
  {
    Intent intent = new Intent(this, PropertyAnimation09.class);
    startActivity(intent);
  }
}
