package com.example.rafaelanastacioalves.moby;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.example.rafaelanastacioalves.moby.pulllisting.PullRequestsActivity;
import com.example.rafaelanastacioalves.moby.pulllisting.PullRequestsFragment;
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import timber.log.Timber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AnyOf.anyOf;

/**
 * Created by rafaelanastacioalves on 6/6/17.
 */


@RunWith(AndroidJUnit4.class)
public class PullMockedListTest {
    private String fileNamePullsListOKRespone = "pulls_list_ok_response.json";

    @Rule
    public ActivityTestRule<PullRequestsActivity> mRepoListTestRule = new ActivityTestRule<PullRequestsActivity>(PullRequestsActivity.class, true, false);

    private MockWebServer server;


    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(), new Bundle());
        server.url("/");
        FlowManager.getDatabase(AppDatabase.class).reset();

//        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));

    }


    @Test
    public void pullListTest() throws IOException {
        Timber.i("pullListTest");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNamePullsListOKRespone)
                )
        );


        Intent intent = new Intent();
        intent.putExtra(PullRequestsFragment.ARG_CREATOR, "elastic");
        intent.putExtra(PullRequestsFragment.ARG_REPOSITORY, "elasticsearch");
        mRepoListTestRule.launchActivity(intent);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(allOf(withId(R.id.pull_textview_title), withText("Update synonym-tokenfilter.asciidoc"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.pull_text_view_description), withText(containsString("1) Correct a typo that refers to the 'graph_synonyms' filter by the name of the")))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.pull_textview_owner_username), withText("elasticsearcher"), withHint("elasticsearcher1"))).check(matches(isDisplayed()));


    }

    @Test
    public void clickTest() throws IOException {
        Timber.i("ClickTest");

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNamePullsListOKRespone)
                )
        );

        Intent intent = new Intent();
        intent.putExtra(PullRequestsFragment.ARG_CREATOR, "elastic");
        intent.putExtra(PullRequestsFragment.ARG_REPOSITORY, "elasticsearch");
        mRepoListTestRule.launchActivity(intent);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String urlString = "https://github.com/elastic/elasticsearch/pull/25280";
        Intents.init();


        onView(allOf(withId(R.id.pull_linear_layout_container), withContentDescription("Pull Request number " + 1))).perform(click());
        intended(anyOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse(urlString)))
        );

        Intents.release();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

}
