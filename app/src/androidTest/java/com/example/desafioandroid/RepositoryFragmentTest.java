package com.example.desafioandroid;

import androidx.test.rule.ActivityTestRule;
import com.example.desafioandroid.view.activity.MainActivity;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class RepositoryFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, false, true);
    @Test
    public void shouldShowDialogIfAmountIsNotInValidInterval(){
        onView(withId(R.id.recycler_repository)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_repository)).perform(click());
        onView(isRoot()).inRoot(isDialog()).check(matches(isDisplayed()));
    }




}

