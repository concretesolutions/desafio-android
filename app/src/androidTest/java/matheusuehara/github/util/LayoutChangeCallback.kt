package matheusuehara.github.util

import android.view.View
import androidx.test.espresso.IdlingResource
import org.hamcrest.Matcher

class LayoutChangeCallback
    internal constructor(
        private val matcher: Matcher<View>
    ) : IdlingResource, View.OnLayoutChangeListener {
        private var callback: IdlingResource.ResourceCallback? = null
        private var matched = false

        override fun getName(): String {
            return "Layout change callback"
        }

        override fun isIdleNow(): Boolean {
            return matched
        }

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
            this.callback = callback
        }

        override fun onLayoutChange(
            v: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            matched = matcher.matches(v)
            callback!!.onTransitionToIdle()
        }
}