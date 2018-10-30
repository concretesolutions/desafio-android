package com.example.feliperecabarren.desafioandroidconcrete;

import android.app.Activity;
import android.content.ComponentName;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



/**
 * Created by felipe.recabarren on 29-10-18.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("com.example.feliperecabarren.desafioandroidconcrete", MainActivity.class);
    }

    Activity activity = getActivity();

    @Test
    public void onCreate() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Attempts to manipulate the UI must be performed on a UI thread.
                // Calling this outside runOnUiThread() will cause an exception.
                //
                // You could also use @UiThreadTest, but activity lifecycle methods
                // cannot be called if this annotation is used.

            }
        });
    }

}
