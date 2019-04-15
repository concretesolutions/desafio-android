package com.dobler.desafio_android;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void gotoPullsPage() {
        sleepTime(4000);
        Espresso.onView(ViewMatchers.withId(R.id.rvMainRepositoryList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        sleepTime(1000);

        Espresso.pressBack();
    }

    @Test
    public void scrollPageTest() {
        // First scroll to the position that needs to be matched and click on it.

        sleepTime(4000);
        Espresso.onView(ViewMatchers.withId(R.id.rvMainRepositoryList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(20, ViewActions.scrollTo()));


        sleepTime(1000);
        Espresso.onView(ViewMatchers.withId(R.id.rvMainRepositoryList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(25, ViewActions.scrollTo()));

    }

    private void sleepTime(int waitRequest) {
        try {
            Thread.sleep(waitRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
