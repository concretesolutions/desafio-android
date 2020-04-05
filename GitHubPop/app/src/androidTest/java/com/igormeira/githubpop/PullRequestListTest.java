package com.igormeira.githubpop;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.igormeira.githubpop.repository.RepositoriesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.igormeira.githubpop.Helper.waitFor;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
public class PullRequestListTest {
    @Rule
    public ActivityTestRule<RepositoriesActivity> rule  = new  ActivityTestRule<>(RepositoriesActivity.class);

    @Test
    public void showingRepositories(){
        waitFor(2000); //Loading
        onView(withText(startsWith("CyC2018")))
                .perform(click());
        waitFor(2000); //Loading
        onView(withText(startsWith("liuwenchn")))
                .check(matches(isDisplayed()));
    }
}