package br.com.appdesafio;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.appdesafio.view.activity.ListRepositoryActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class ListRepositoryActivityTest {




    @Rule
    public ActivityTestRule<ListRepositoryActivity> mActivityRule =
            new ActivityTestRule<>(ListRepositoryActivity.class, false, true);


    /**
     * Check the repository list.
     */

   @Test
    public void testCheckListRepository() {
        SystemClock.sleep(7000);
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.item_progress_bar)).check(matches(not(isDisplayed())));
    }

    /**
     * Checks if the repository name is visible.
     */
    @Test
    public void testNameRepository() {
        testCheckListRepository();
        onView(allOf(withId(R.id.txt_name_repository),
                withText("java-design-patterns"))).check(matches(isDisplayed()));
    }

    /**
     *  Checks whether the pull request list is displayed when you click a repository.
     */
    @Test
    public void testOnClickListRepository() {
        SystemClock.sleep(5000);
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(5000);
        onView(withId(R.id.recycler_pull_request)).check(matches(isDisplayed()));


    }



}
