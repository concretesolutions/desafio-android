package com.example.luisguzman.desafio_android;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static org.hamcrest.Matchers.allOf;


import static android.support.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)

public class PullReqEspressoTest {
    @Rule
    public ActivityTestRule<PullReqActivity> pullReqActivityActivityTestRule =
            new ActivityTestRule<>(PullReqActivity.class);

    @Test
    public void checkPullRequestLink() {
        String repositoryName = "java-design-patterns";
        String repositoryUser = "iluwatar";

        Intent i =  new Intent();
        i.putExtra("name", repositoryName);
        i.putExtra("owner", repositoryUser);
        pullReqActivityActivityTestRule.launchActivity(i);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.pullReq_list)).check(matches(isDisplayed()));



    }
}
