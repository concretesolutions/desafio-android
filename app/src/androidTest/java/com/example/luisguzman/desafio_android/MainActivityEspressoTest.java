package com.example.luisguzman.desafio_android;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import android.support.test.espresso.contrib.RecyclerViewActions;
import com.example.luisguzman.desafio_android.adapter.RepoAdapter;








@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    public static final String VALID_LANGUAGE = "Java";


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void spinnerLanguagesChange() {
        onView(withId(R.id.languages)).check(matches(isDisplayed()));

        onView(withId(R.id.languages)).check(matches(withId(R.id.languages))).perform();

        onView(withId(R.id.languages)).check(matches(withSpinnerText(containsString(VALID_LANGUAGE))));

    }

    @Test
    public void evaluateAdapterList(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.repositories_list);

        Assert.assertTrue(recyclerView.getAdapter().getItemCount() > 0);
    }

    @Test
    public void infiniteScrollTest() {
        // wait to load first items
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // scrolls to last item
        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.repositories_list);
        int lastItemPosition = recyclerView.getAdapter().getItemCount() - 1;

        onView(withId(R.id.repositories_list)).perform(RecyclerViewActions.scrollToPosition(lastItemPosition));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int newlastItemPosition = recyclerView.getAdapter().getItemCount() - 1;

        onView(withId(R.id.repositories_list))
                .perform(RecyclerViewActions.scrollToPosition(newlastItemPosition));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int newNewlastItemPosition = recyclerView.getAdapter().getItemCount() - 1;

        onView(withId(R.id.repositories_list))
                .perform(RecyclerViewActions.scrollToPosition(newNewlastItemPosition));
    }


}
