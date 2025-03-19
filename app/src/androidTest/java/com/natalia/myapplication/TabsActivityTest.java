package com.natalia.myapplication;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TabsActivityTest {

    @Rule
    public ActivityTestRule<TabsActivity> activityRule =
            new ActivityTestRule<>(TabsActivity.class);

    @Test
    public void testNavigationToMapsFragment() {
        Espresso.onView(withText(R.string.tab_text_2)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.map)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testNavigationToListFragment() {
        Espresso.onView(withText(R.string.tab_text_1)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.home)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testButtonQRCode() {
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());
    }

}
