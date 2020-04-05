package com.igormeira.githubpop;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.githubpop.pullrequest.PullRequestsActivity;
import com.igormeira.githubpop.repository.RepositoriesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.igormeira.githubpop.Helper.getActivityInstance;
import static com.igormeira.githubpop.Helper.waitFor;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
public class RepositoryNavigationTest {
    @Rule
    public ActivityTestRule<RepositoriesActivity> rule
            = new ActivityTestRule<>(RepositoriesActivity.class);

    @Test
    public void goToPullRequest() {
        waitFor(2000);
        onView(withText(startsWith("CyC2018")))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PullRequestsActivity);
        assertTrue(expected);
    }

}
