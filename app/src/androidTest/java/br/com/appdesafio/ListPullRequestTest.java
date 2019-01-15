package br.com.appdesafio;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.appdesafio.view.activity.ListPullRequestActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class ListPullRequestTest {


    @Rule
    public ActivityTestRule<ListPullRequestActivity> mActivityRule =
            new ActivityTestRule<>(ListPullRequestActivity.class, true, false);


    /**
     * Launcher the ListPullRequestActivity activity so that the tests are started.
     */
    @Test
    public void intent() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("creator", "iluwatar");
        bundle.putSerializable("repository", "java-design-patterns");
        Intent intent = new Intent();
        intent.putExtras(bundle);
        mActivityRule.launchActivity(intent);

    }

    /**
     * Check the pull request list.
     */
    @Test
    public void testCheckListPullRequest() {
        SystemClock.sleep(2000);
        intent();
        onView(withId(R.id.recycler_pull_request)).check(matches(isDisplayed()));
        onView(withId(R.id.item_progress_bar)).check(matches(not(isDisplayed())));
    }


    /**
     *  Checks if the browser was opened with the pull request data.
     */
    @Test
    public void testOpenPullRequestBrowser() {
        testCheckListPullRequest();
        onView(withId(R.id.recycler_pull_request)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        ListPullRequestActivity activity = mActivityRule.getActivity();
        boolean isOnresume = activity.stateOnResume;
        Assert.assertEquals(true, isOnresume);


    }

    /**
     *  Check the name of the user who the pull request.
     */
    @Test
    public void testUserNamePullRequest() {
        testCheckListPullRequest();
        onView(allOf(withId(R.id.txt_name_user),
                withText("kezhenxu94"))).check(matches(isDisplayed()));
    }


}
