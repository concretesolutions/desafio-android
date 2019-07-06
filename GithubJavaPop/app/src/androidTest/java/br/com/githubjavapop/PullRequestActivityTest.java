package br.com.githubjavapop;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.githubjavapop.activity.MainActivity;
import br.com.githubjavapop.activity.PullRequestActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PullRequestActivityTest {

    @Rule
    public ActivityTestRule<PullRequestActivity> mActivityRule = new ActivityTestRule<>(PullRequestActivity.class,
            false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.tbrPullRequest)).check(matches(isDisplayed()));
        onView(withId(R.id.rccPull)).check(matches(isDisplayed()));
        onView(withId(R.id.imagemVoltar)).check(matches(isDisplayed()));
    }

    public void clickOnBackButton_shouldOpenMainActivity() {
        Intents.init();

        Matcher<Intent> matcher = hasComponent(MainActivity.class.getName());

        onView(withId(R.id.imagemVoltar)).perform(click());

        intended(matcher);

        Intents.release();
    }

}
