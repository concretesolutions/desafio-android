package com.example.rafaelanastacioalves.moby;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.example.rafaelanastacioalves.moby.repolisting.RepoListingActivity;
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeoutException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import timber.log.Timber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper.withHolderTitleView;
import static com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper.withRecyclerView;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by rafaelanastacioalves on 6/6/17.
 */


@RunWith(AndroidJUnit4.class)
public class RepoMockedListTest {
    private String fileNameRepoListOKRespone = "repo_list_ok_response.json";
    private String fileNameRepoListOKPage2Respone = "repo_list_ok_page_2_response.json";
    private String fileNamePullsListOKRespone = "pulls_list_ok_response.json";

    @Rule
    public ActivityTestRule<RepoListingActivity> mRepoListTestRule = new ActivityTestRule(RepoListingActivity.class, true, false);

    private MockWebServer server;

    private IdlingResource mIdlingResource;


    @Before
    public void setUp() throws Exception {

        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(), new Bundle());
        server.url("/").toString();
        FlowManager.getDatabase(AppDatabase.NAME).reset();

    }


    @Test
    public void JSONValuesFulfill() throws IOException, TimeoutException, InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameRepoListOKRespone)
                )
        );
        Intent intent = new Intent();
        mIdlingResource = mRepoListTestRule.launchActivity(intent).getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);


        onView(allOf(withId(R.id.repo_text_view_title), withText("elasticsearch"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_text_view_description), withText("Open Source, Distributed, RESTful Search Engine"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_textview_number_forks), withText("8177"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_textview_number_stars), withText("23226"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_owner_photo), withContentDescription("elasticsearch"))).check(matches(isDisplayed()));


    }

    @Test
    public void screenTransition() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameRepoListOKRespone)
                )
        );

        server.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNamePullsListOKRespone)
                )
        );

        Intent intent = new Intent();
        mIdlingResource = mRepoListTestRule.launchActivity(intent).getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(allOf(withId(R.id.repo_linear_layout_container), withContentDescription("elasticsearch")))
                .perform(click());
        onView(withId(R.id.pull_list_fragment)).check(matches(isDisplayed()));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Test
    public void paginationTest() throws IOException, InterruptedException {
        Timber.i("paginationTest");
        boolean initialListEmpty = true;



        server.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameRepoListOKRespone)
                )
        );

        server.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameRepoListOKPage2Respone)
                )
        );

        Intent intent = new Intent();
        mIdlingResource = mRepoListTestRule.launchActivity(intent).getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);


        onView(withRecyclerView(R.id.repo_list).atPosition(1)).check(matches(isDisplayed()));
        onView(withId(R.id.repo_list)).perform(scrollToHolder(withHolderTitleView("interviews")));

        onView(withId(R.id.repo_list)).perform(scrollToPosition(30));

        onView(allOf(withId(R.id.repo_text_view_title), withText("fastjson"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_textview_number_forks), withText("3281"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_textview_number_stars), withText("9476"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.repo_linear_layout_container), withContentDescription("fastjson"))).check(matches(isDisplayed()));


    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
        if (mIdlingResource != null) {
            Timber.d("... mIdlingResource is not null");

            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}
