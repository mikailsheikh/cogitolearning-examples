package uk.co.cogitolearning.sceneexample;


import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity
{

  ViewGroup container;
  Scene webSiteForm;
  Scene pictureForm;
  Scene contactForm;
  Scene eventForm;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    container = (ViewGroup) findViewById(R.id.main_container);
    webSiteForm = Scene.getSceneForLayout(container, R.layout.scene_link, this);
    pictureForm = Scene.getSceneForLayout(container, R.layout.scene_picture, this);
    contactForm = Scene.getSceneForLayout(container, R.layout.scene_contact, this);
    eventForm   = Scene.getSceneForLayout(container, R.layout.scene_event, this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void showWebSiteForm(View view)
  {
    TransitionManager.go(webSiteForm);
  }

  public void showPictureForm(View view)
  {
    TransitionManager.go(pictureForm);
  }

  public void showContactForm(View view)
  {
    TransitionManager.go(contactForm);
  }

  public void showEventForm(View view)
  {
    TransitionManager.go(eventForm);
  }
}
