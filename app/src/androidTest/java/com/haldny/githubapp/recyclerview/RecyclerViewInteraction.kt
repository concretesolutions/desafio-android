package com.haldny.githubapp.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import org.hamcrest.Matcher

class RecyclerViewInteraction<A> private constructor(private val viewMatcher: Matcher<View>) {

    companion object {
        fun <A> onRecyclerView(viewMatcher: Matcher<View>): RecyclerViewInteraction<A> =
            RecyclerViewInteraction(viewMatcher)
    }

    private lateinit var items: List<A>

    fun withItems(items: List<A>): RecyclerViewInteraction<A> {
        this.items = items
        return this
    }

    fun check(assertion: (item: A, view: View, e: NoMatchingViewException?) -> Unit): RecyclerViewInteraction<A> {
        items.indices.map {
            onView(viewMatcher)
                .perform(scrollToPosition<RecyclerView.ViewHolder>(it))
                .check(RecyclerItemViewAssertion(it, items[it], assertion))
        }
        return this
    }
}