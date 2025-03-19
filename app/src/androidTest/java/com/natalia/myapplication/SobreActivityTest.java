package com.natalia.myapplication;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SobreActivityTest {

    @Rule
    public ActivityTestRule<SobreActivity> activityRule =
            new ActivityTestRule<>(SobreActivity.class);

    @Test
    public void testMenuNavigationToSobreActivity() {
        Espresso.onView(withId(R.id.toolbar)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.about)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testButtonSobreActivity() {
        Espresso.onView(withId(R.id.fab2)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.home)).check(ViewAssertions.matches(isDisplayed()));
    }
}
