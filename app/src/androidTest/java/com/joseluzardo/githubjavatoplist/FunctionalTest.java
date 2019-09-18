package com.joseluzardo.githubjavatoplist;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.joseluzardo.githubjavatoplist.Activities.PullRequestActivity;
import com.joseluzardo.githubjavatoplist.Activities.ReposListActivity;
import com.joseluzardo.githubjavatoplist.Adapters.ReposAdapter;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FunctionalTest {

    @Rule
    public ActivityTestRule<ReposListActivity> mActivityTestRule = new ActivityTestRule<>(ReposListActivity.class);


    @Before
    public void setup(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRecyclerView() {


        onView(ViewMatchers.withId(R.id.fabLayout)).perform(click());

        onView(withText("JavaGuide")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.rvRepos))
                .perform(RecyclerViewActions.scrollToPosition(10));

        onView(ViewMatchers.withId(R.id.rvRepos))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        click()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Leader Election Pattern")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.rvPullList))
                .perform(RecyclerViewActions.scrollToPosition(3));

        onView(ViewMatchers.withId(R.id.rvPullList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        click()));

    }

}