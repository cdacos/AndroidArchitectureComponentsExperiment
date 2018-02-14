package com.cysmic.aacx;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith (AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Rule
  public ActivityTestRule<TargetListActivity> activityTestRule = new ActivityTestRule<>(TargetListActivity.class);

  @Test
  public void useAppContext() throws Exception {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    assertEquals("com.cysmic.aacx", appContext.getPackageName());
  }

  @Test
  public void testExample() {
    onView(withId(R.id.target_list_message))
        .check(matches(isDisplayed()))
        .check(matches(withText(containsString("Found"))));
  }
}
