package br.com.desafio.ui;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import br.com.desafio.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RepositoryActivityTest {
    @Rule
    public final ActivityTestRule<RepositoryActivity_> main = new ActivityTestRule<>(RepositoryActivity_.class);

    @Test
    public void testRepositoriesDisplayer(){
        onView(withId(R.id.repostories)).check(ViewAssertions.matches(isCompletelyDisplayed()));
    }
}
