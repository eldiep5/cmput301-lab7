package com.example.androiduitesting;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

public class ShowActitivtyTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }
    @After
    public void reset(){
        Intents.release();
    }

    @Test
    public void testActivitySwitch() {
        //Make City
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //Click on City (Help from https://stackoverflow.com/questions/28061300/espresso-click-a-single-list-view-item)
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //Ensures Activity is switched to Show Activity (Help from https://stackoverflow.com/questions/25998659/espresso-how-can-i-check-if-an-activity-is-launched-after-performing-a-certain)
        intended(hasComponent(ShowActivity.class.getName()));
    }

    @Test
    public void testCityConsistent() {
        //Make City
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //Click on City (Help from https://stackoverflow.com/questions/28061300/espresso-click-a-single-list-view-item)
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //Ensures same City as Button is shown
        onView(withText("Edmonton")).check(matches(isDisplayed()));


    }
    @Test
    public void testBackButton() {
        //Make City
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());
        //Click on City (Help from https://stackoverflow.com/questions/28061300/espresso-click-a-single-list-view-item)
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //Go back to MainActivity
        onView(withId(R.id.BackButton)).perform(click());

        //Ensures back button sends user back to MainActiviy by checking if addcity button is displayed
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));


    }
}
