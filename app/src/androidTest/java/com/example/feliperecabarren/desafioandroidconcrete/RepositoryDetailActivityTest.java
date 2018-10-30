package com.example.feliperecabarren.desafioandroidconcrete;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by felipe.recabarren on 30-10-18.
 */

public class RepositoryDetailActivityTest  extends ActivityInstrumentationTestCase2<RepositoryDetailActivity> {


    public RepositoryDetailActivityTest() {
        super("com.example.feliperecabarren.desafioandroidconcrete", RepositoryDetailActivity.class);
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
        onView(withId(R.id.recyclerViewRepositorioDetail)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

}