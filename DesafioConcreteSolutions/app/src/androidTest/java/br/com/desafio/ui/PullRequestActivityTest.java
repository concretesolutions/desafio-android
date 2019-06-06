package br.com.desafio.ui;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.desafio.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class PullRequestActivityTest {
    @Rule
    public final ActivityTestRule<PullRequestActivity_> main = new ActivityTestRule<>(PullRequestActivity_.class);

    @Test
    public void testPullRequestsDisplayer(){
        onView(withId(R.id.pullRequests)).check(ViewAssertions.matches(isCompletelyDisplayed()));
    }
}
