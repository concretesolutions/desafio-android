package com.example.desafio_concrete;


import android.view.View;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
public class MainActivityTeste {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void thereIsRecyclerListInAppLaunch() throws InterruptedException {

        //waiting the activity start completaly
        Thread.sleep(10000);
        onView(withId(R.id.recyclerview_repository)).check(matches(isDisplayed()));
    }



    @Test
    public void scrollAndClickRecyclerViewList() throws InterruptedException {

        //waiting the activity start completaly
        Thread.sleep(10000);
        onView(withId(R.id.recyclerview_repository))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
    }


    @Test
    public void backNavigationToListRepository() throws InterruptedException {

        Thread.sleep(10000);
        onView(withId(R.id.recyclerview_repository))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));

        pressBack();
        onView(withId(R.id.recyclerview_repository)).check(matches(isDisplayed()));

    }










}
