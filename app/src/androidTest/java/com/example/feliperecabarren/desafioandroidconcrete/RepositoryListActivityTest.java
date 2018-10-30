package com.example.feliperecabarren.desafioandroidconcrete;

/**
 * Created by felipe.recabarren on 29-10-18.
 */
import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoryListActivityTest  extends ActivityInstrumentationTestCase2<RepositoryListActivity> {

    public RepositoryListActivityTest() {
        super("com.example.feliperecabarren.desafioandroidconcrete", RepositoryListActivity.class);
    }

    Activity activity;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        // Injecting the Instrumentation instance is required
        // for your test to run with AndroidJUnitRunner.
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Test
    public void testActivityNotNull() {
        assertNotNull(activity);
    }

    @Test
    public void ClickRecyclerView(){
        onView(withId(R.id.recyclerViewRepositorio)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }




}
