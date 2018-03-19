package com.example.android.bakingapp.ui;

import android.os.AsyncTask;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.base.IdlingResourceRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Santosh on 21-02-2018.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule public ActivityTestRule<Recipe> activityTest=new ActivityTestRule<>(Recipe.class);


     @Test
      public void TestFunction(){
          onView((withContentDescription("main_image")));
          new AsyncTask<Void,Void,Void>(){
              @Override
              protected Void doInBackground(Void... voids) {
                  try{
                      Thread.sleep(5000);
                  }catch (InterruptedException e){
                      e.printStackTrace();
                  }
                  return null;
              }

              @Override
              protected void onPostExecute(Void aVoid) {
                  onView(withId(R.id.Recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
                  onView(withId(R.id.Recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
                  onView(withId(R.id.Recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
                  onView(withId(R.id.Recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

              }
          };




     }

}