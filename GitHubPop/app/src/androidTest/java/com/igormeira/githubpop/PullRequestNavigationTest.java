package com.igormeira.githubpop;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.igormeira.githubpop.pullrequest.PullRequestsActivity;
import com.igormeira.githubpop.repository.RepositoriesActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.igormeira.githubpop.Helper.getActivityInstance;
import static com.igormeira.githubpop.Helper.waitFor;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class PullRequestNavigationTest {
    @Rule
    public ActivityTestRule<RepositoriesActivity> rule
            = new ActivityTestRule<>(RepositoriesActivity.class);

    @Test
    public void goToPullRequestAndBack() {
        waitFor(2000);
        onView(withText(startsWith("CyC2018")))
                .perform(click());
        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PullRequestsActivity);
        assertTrue(expected);

        waitFor(2000);
        onView(withId(R.id.backButton))
                .check(matches(isDisplayed()))
                .perform(click());
        Activity backActivity = getActivityInstance();
        boolean backExpected = (backActivity instanceof RepositoriesActivity);
        assertTrue(backExpected);
    }

    @Test
    public void goToWeb() {
        waitFor(2000);
        onView(withText(startsWith("CyC2018")))
                .perform(click());

        waitFor(2000);
        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://api.github.com/repos/CyC2018/CS-Notes/pulls/903"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));
        onView(withText(startsWith("liuwenchn")))
                .perform(click());
        intended(expectedIntent);
        Intents.release();
    }

}
