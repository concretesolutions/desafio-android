package com.concrete.challenge.githubjavapop;

import android.app.Instrumentation;
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
import com.concrete.challenge.githubjavapop.ui.pull.PullRequestFragment;
import com.concrete.challenge.githubjavapop.ui.pull.PullRequestRecyclerViewAdapter;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
public class PullRequestActivityTest {
    @Rule
    public ActivityTestRule<PullRequestActivity> rule  = new  ActivityTestRule<>(PullRequestActivity.class, true, false);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private IdlingResource idlingResource;

    @Before
    public void setup() {
        Intents.init();
        Intent intent = new Intent()
                .putExtra(PullRequestActivity.USER_NAME_KEY, "elastic")
                .putExtra(PullRequestActivity.REPOSITORY_NAME_KEY, "elasticsearch");
        rule.launchActivity(intent);
        idlingResource = (IdlingResource) rule.getActivity().getSupportFragmentManager().findFragmentByTag(PullRequestFragment.class.getName());
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void testHasData() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request)).check((view, noViewFoundException) -> {
            RecyclerView viewById = rule.getActivity().requireViewById(R.id.recycler_view_pull_request);
            PullRequestRecyclerViewAdapter adapter = (PullRequestRecyclerViewAdapter) viewById.getAdapter();
            Assert.assertThat(adapter.getItemCount(), Matchers.greaterThan(0));
        });
    }

    @Test
    public void testInfinityScroll() {
        AtomicReference<PullRequestRecyclerViewAdapter> adapter = new AtomicReference<>();
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request)).check((view, noViewFoundException) -> {
            RecyclerView viewById = rule.getActivity().requireViewById(R.id.recycler_view_pull_request);
            adapter.set((PullRequestRecyclerViewAdapter) viewById.getAdapter());
        });

        int firstPageCount = adapter.get().getItemCount();

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request))
                .perform(RecyclerViewActions.scrollToPosition(adapter.get().getItemCount() - 1));

        Assert.assertThat(adapter.get().getItemCount(), Matchers.greaterThan(firstPageCount));
    }

    @Test
    public void testNavigateToBrowser() {
        AtomicReference<PullRequestRecyclerViewAdapter> adapter = new AtomicReference<>();
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request)).check((view, noViewFoundException) -> {
            RecyclerView viewById = rule.getActivity().requireViewById(R.id.recycler_view_pull_request);
            adapter.set((PullRequestRecyclerViewAdapter) viewById.getAdapter());
            Assert.assertThat(adapter.get().getItemCount(), Matchers.greaterThan(0));
        });

        String url = adapter.get().getPullRequests().get(0).htmlUrl;

        Matcher<Intent> intentMatcher = CoreMatchers.allOf(IntentMatchers.hasAction(Intent.ACTION_VIEW), IntentMatchers.hasData(url));
        Intents.intending(intentMatcher).respondWith(new Instrumentation.ActivityResult(0, null));

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Intents.intended(intentMatcher);
    }

    @After
    public void destroy() {
        IdlingRegistry.getInstance().unregister(idlingResource);
        Intents.release();
    }
}
