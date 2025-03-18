package com.natalia.myapplication;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DadosTempoTest {

    @Rule
    public ActivityTestRule<TabsActivity> activityRule =
            new ActivityTestRule<>(TabsActivity.class);

    @Test
    public void testWeatherDataDisplay() {
        Espresso.onView(withId(R.id.cityText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.tempNow)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.card01)).check(matches(isDisplayed()));
    }

    @Test
    public void testWeatherDataWeekDisplay() {
        Espresso.onView(withId(R.id.dateText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.minText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.maxText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.card02)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testWeatherImageLoading() {
        ViewInteraction weatherImageView = Espresso.onView(withId(R.id.imageNow));
        weatherImageView.check(matches(isDisplayed()));
    }

}
