package sergio.com.br.desafioandroid.ui.activities

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.DrawableRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.models.PullRequestModel
import sergio.com.br.desafioandroid.ui.activities.pull_request.PullRequestActivity
import sergio.com.br.desafioandroid.utils.MockUtils


@LargeTest
@RunWith(AndroidJUnit4::class)
class PullRequestActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(PullRequestActivity::class.java, false, false)

    lateinit var myActivity: PullRequestActivity
    var pullRequestsList: ArrayList<PullRequestModel> = ArrayList()


    @Before
    fun setupItems() {
        val intent = Intent()
        intent.putExtra("ownerName", "Test")
        intent.putExtra("repositoryName", "Repository Test")
        intent.putExtra("isTesting", true)

        mActivityTestRule.launchActivity(intent)
        myActivity = mActivityTestRule.activity
        myActivity.pullRequestViewModel.hasLoaded = true

        pullRequestsList = MockUtils.loadPullRequestList(myActivity)
        myActivity.pullRequestViewModel.pullRequestMutableLiveData.postValue(pullRequestsList)
    }


    @Test
    fun userNameTest() {
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.userNameText),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.mainLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.recyclerView),
                            1
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )

        textView.check(ViewAssertions.matches(ViewMatchers.withText("Lich King")))
    }

    @Test
    fun createdAtTest() {
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.createdAtText),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.mainLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.recyclerView),
                            1
                        )
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )

        textView.check(ViewAssertions.matches(ViewMatchers.withText("${myActivity.getString(R.string.created_at_text)} 08/06/2019")))
    }

    @Test
    fun userPlaceholderTest() {
        val imageView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.userImage),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.mainLayout),
                        childAtPosition(
                            ViewMatchers.withId(R.id.recyclerView),
                            2
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )

        imageView.check(ViewAssertions.matches(drawableIsCorrect(R.drawable.user_placeholder)))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun drawableIsCorrect(@DrawableRes drawableResId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with drawable from resource id: ")
                description.appendValue(drawableResId)
            }

            override fun matchesSafely(target: View?): Boolean {
                if (target !is ImageView) {
                    return false
                }
                if (drawableResId < 0) {
                    return target.drawable == null
                }
                val expectedDrawable = ContextCompat.getDrawable(target.context, drawableResId)
                    ?: return false

                val bitmap = (target.drawable as BitmapDrawable).bitmap
                val otherBitmap = (expectedDrawable as BitmapDrawable).bitmap
                return bitmap.sameAs(otherBitmap)
            }
        }
    }
}