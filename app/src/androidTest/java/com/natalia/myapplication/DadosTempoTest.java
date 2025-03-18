package com.natalia.myapplication;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DadosTempoTest {

    @Rule
    public ActivityTestRule<TabsActivity> activityRule =
            new ActivityTestRule<>(TabsActivity.class);

    @Test
    public void testWeatherImageLoading() {
        ViewInteraction weatherImageView = Espresso.onView(withId(R.id.imageNow));
        weatherImageView.check(matches(isDisplayed()));
    }

    @Test
    public void testWeatherDataDisplay() {
        Espresso.onView(withId(R.id.cityText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.tempNow)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.card01)).check(matches(isDisplayed()));
    }

    @Test
    public void testWeatherDataWeekDisplay() {

        Espresso.onView(withId(R.id.my_recycler_view)).check(matches(ViewMatchers.isDisplayed()));


        String[] day = {"02/11", "03/11", "04/11", "05/11", "06/11", "07/11", "08/11"};
        String[] weekDay = {"Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"};
        int[] minTemps = {21, 24, 18, 19, 23, 27, 29};
        int[] maxTemps = {35, 33, 25, 27, 32, 36, 35};

        for (int i = 0; i < 7; i++) {
            Espresso.onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.scrollToPosition(i));

            Espresso.onView(withText(day[i] + " - " + weekDay[i])).check(matches(ViewMatchers.isDisplayed()));
            Espresso.onView(withText(minTemps[i] + "º")).check(matches(ViewMatchers.isDisplayed()));
            Espresso.onView(withText(maxTemps[i] + "º")).check(matches(ViewMatchers.isDisplayed()));
            Espresso.onView(withId(R.id.card02)).check(matches(isDisplayed()));
        }
    }
}
