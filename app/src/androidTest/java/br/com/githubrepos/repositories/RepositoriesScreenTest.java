package br.com.githubrepos.repositories;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.githubrepos.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoriesScreenTest {

    @Rule
    public ActivityTestRule<RepositoriesActivity> repositoriesActivityTestRule =
            new ActivityTestRule<>(RepositoriesActivity.class);

    private String repositoryName0 = "repositoryName0";
    private String repositoryName8 = "repositoryName8";


    // A custom Matcher which matches an item in a RecyclerView by its text.
    // View constraints:
    // - View must be a child of a RecyclerView
    // - param itemText the text to match
    // - return Matcher that matches text in the given view
    private Matcher<View> withItemText(final String itemText) {
        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty");
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View item) {
                return allOf(isDescendantOfA(isAssignableFrom(RecyclerView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is isDescendantOfA RV with text " + itemText);
            }
        };
    }

    public BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder> withItemViewHolder(
            final String withText) {

        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>
                (RecyclerView.ViewHolder.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with text: " + withText);
            }

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder item) {
                if (item.getItemViewType() == 0) {
                    RepositoriesAdapter.ProgressViewHolder progressViewHolder =
                            (RepositoriesAdapter.ProgressViewHolder) item;
                    return false;
                } else {
                    RepositoriesAdapter.ItemViewHolder itemViewHolder =
                            (RepositoriesAdapter.ItemViewHolder) item;

                    return itemViewHolder.tvRepositoryName.getText().equals(withText);
                }
            }
        };
    }


    @Before
    public void registerIdlingResource() {
        //https://medium.com/azimolabs/wait-for-it-idlingresource-and-conditionwatcher-602055f32356#.vsyeol9wp
        Espresso.registerIdlingResources(repositoriesActivityTestRule.getActivity().getCountingIdlingResource());

        //Prepare your test fixture for this test. In this case we register an IdlingResources with
        //Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
        //idle state. This helps Espresso to synchronize your test actions, which makes tests significantly
        //more reliable.
    }

    @After
    public void unregisterIdlingResource() {
        //Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
        Espresso.unregisterIdlingResources(repositoriesActivityTestRule.getActivity().getCountingIdlingResource());
    }


    @Test
    public void loadRepositoryList_showOnScreen() {
        //scroll to the item and check if its displayed
        onView(withId(R.id.repositories_list))
                .perform(scrollToPosition(5)).check(matches(isDisplayed()));

        //Another way
        //onView(withId(R.id.repositories_list)).perform(scrollTo(hasDescendant(withText(repositoryName0))));
        //onView(withItemText(repositoryName0)).check(matches(isDisplayed()));
    }

    @Test
    public void loadRepositoryListScrollDown_showOnScreen() {
        onView(withId(R.id.repositories_list))
                .perform(scrollToPosition(9), scrollToPosition(19), scrollToPosition(29))
                .check(matches(isDisplayed()));

        //Another way
        //onView(withItemText("repositoryName29")).check(matches(isDisplayed()));

        //Another way
        //onView(withId(R.id.repositories_list)).perform(scrollToHolder(withItemViewHolder("repositoryName9")));
        //onView(withId(R.id.repositories_list)).perform(scrollToHolder(withItemViewHolder("repositoryName19")));
    }

    @Test
    public void clickRepository_openNewScreen() {

        onView(withId(R.id.repositories_list))
                .perform(scrollToPosition(4), click());
        //        .perform(scrollTo(hasDescendant(withText("repositoryName4"))), click());

        onView(withText("PullRequests")).check(matches(isDisplayed()));
    }

    @Test
    public void longClickRepository_changeActionBarIcons() {
        onView(withId(R.id.repositories_list))
                .perform(scrollToPosition(5), longClick());
        //        .perform(scrollTo(hasDescendant(withText("repositoryName5"))), longClick());

        onView(withText("Repositories")).check(doesNotExist());
        onView(withId(R.id.delete_repository)).check(matches(isDisplayed()));
    }

    @Test
    public void clickBackButton_unselectRepository() {
        onView(withId(R.id.repositories_list))
                .perform(scrollToPosition(5), longClick());
        //        .perform(scrollTo(hasDescendant(withText("repositoryName5"))), longClick());

        String actionBarUpDescription = repositoriesActivityTestRule.getActivity()
                .getString(android.support.v7.appcompat.R.string.abc_action_bar_up_description);
        onView(withContentDescription(actionBarUpDescription)).perform(click());

        onView(withText("Repositories")).check(matches(isDisplayed()));
        onView(withId(R.id.delete_repository)).check(doesNotExist());
    }

    @Test
    public void clickDeleteButton_deleteRepository() {
        //scrolls to repository
        onView(withId(R.id.repositories_list)).perform(scrollToPosition(3));

        //select repository
        onView(withItemText("repositoryName2")).perform(longClick());
        //click delete button
        onView(withId(R.id.delete_repository)).perform(click());
        //check if item not displayed more
        onView(withItemText("repositoryName2")).check(doesNotExist());

        onView(withText("Repositories")).check(matches(isDisplayed()));
        onView(withId(R.id.delete_repository)).check(doesNotExist());
    }

}
