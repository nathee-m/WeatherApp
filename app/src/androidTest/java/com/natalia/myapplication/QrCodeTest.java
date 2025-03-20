package com.natalia.myapplication;

import android.app.Activity;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.journeyapps.barcodescanner.CaptureActivity;

@RunWith(AndroidJUnit4.class)
public class QrCodeTest {

    @Rule
    public ActivityScenarioRule<TabsActivity> activityRule =
            new ActivityScenarioRule<>(TabsActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testButtonQRCode() {
        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());
    }

    @Test
    public void testCaptureActivityOpened() {

        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(CaptureActivity.class.getName()));
    }

    @Test
    public void testCaptureActivityCanceled() throws UiObjectNotFoundException {

        Espresso.onView(withId(R.id.fab)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(CaptureActivity.class.getName()));
        UiDevice device = UiDevice.getInstance(androidx.test.platform.app.InstrumentationRegistry.getInstrumentation());

        UiObject2 allowButton = device.wait(Until.findObject(By.text("Only this time")), 10000);

        if (allowButton != null) {
            allowButton.click();
        } else {
            throw new UiObjectNotFoundException("Botão 'Only this time' não encontrado");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.pressBack();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("SCAN_RESULT", "");
        activityRule.getScenario().onActivity(activity -> {
            activity.onActivityResult(Activity.RESULT_CANCELED, Activity.RESULT_CANCELED, resultIntent);
        });

        Espresso.onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }
}
