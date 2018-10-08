package com.github.api.morepopulargithubapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.api.morepopulargithubapp.view.MainActivity_;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NavigationActivitiesTest {

    // Activity a ser lançada
    @Rule
    public ActivityTestRule<MainActivity_> mActivityTestRule = new ActivityTestRule<>(MainActivity_.class);

    /**
     * Valida a navegação entre as activities Main e PullRequest até o web site
     */
    @Test
    public void clickReciclerViewItem_openPullRequestActivity(){
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

         onView(withId(R.id.recyclerViewPullRequest))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


}
