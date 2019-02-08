import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.accenture.githubrepositories.MainActivity
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.accenture.githubrepositories.R
import kotlinx.android.synthetic.main.content_main.*


import org.junit.Rule
import org.junit.Test

class ResposRecyclerViewTest {

    @get:Rule
    var activity: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun navigateFlow() {

        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler_main_repos_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.longClick()))

        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recycler_pull_req_frag_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.longClick()))

        Thread.sleep(5000)
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(isRoot()).perform(ViewActions.pressBack());

        Thread.sleep(2000)
        onView(withId(R.id.recycler_main_repos_view))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(activity.activity.recycler_main_repos_view.getAdapter().getItemCount() - 1))
    }

}
