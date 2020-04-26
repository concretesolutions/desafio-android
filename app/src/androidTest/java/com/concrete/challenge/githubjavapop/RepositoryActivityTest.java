package com.concrete.challenge.githubjavapop;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.concrete.challenge.githubjavapop.ui.pull.PullRequestActivity;
import com.concrete.challenge.githubjavapop.ui.repository.RepositoryActivity;
import com.concrete.challenge.githubjavapop.ui.repository.RepositoryFragment;
import com.concrete.challenge.githubjavapop.ui.repository.RepositoryRecyclerViewAdapter;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RepositoryActivityTest {
    @Rule
    public ActivityTestRule<RepositoryActivity> rule  = new  ActivityTestRule<>(RepositoryActivity.class, true, false);

    private IdlingResource idlingResource;

    @Before
    public void setup() {
        Intents.init();
        rule.launchActivity(new Intent());
        idlingResource = (IdlingResource) rule.getActivity().getSupportFragmentManager().findFragmentByTag(RepositoryFragment.class.getName());
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void testHasData() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_repository)).check((view, noViewFoundException) -> {
            RecyclerView viewById = rule.getActivity().requireViewById(R.id.recycler_view_repository);
            RepositoryRecyclerViewAdapter adapter = (RepositoryRecyclerViewAdapter) viewById.getAdapter();
            Assert.assertThat(adapter.getItemCount(), Matchers.greaterThan(0));
        });
    }

    @Test
    public void testNavigateToPullRequests() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_repository))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Intents.intended(IntentMatchers.hasComponent(PullRequestActivity.class.getName()), Intents.times(1));
    }

    @After
    public void destroy() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        Intents.release();
    }
}
