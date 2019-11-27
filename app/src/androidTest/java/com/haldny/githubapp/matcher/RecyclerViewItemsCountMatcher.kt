package com.haldny.githubapp.matcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class RecyclerViewItemsCountMatcher(private val expectedItemCount: Int,
                                    private var size: Int? = 0) : BaseMatcher<View>() {

    companion object {
        fun recyclerViewHasItemCount(itemCount: Int): Matcher<View> =
            RecyclerViewItemsCountMatcher(itemCount)
    }

    override fun matches(item: Any): Boolean {
        val recyclerView = item as RecyclerView
        size = recyclerView.adapter?.itemCount
        return recyclerView.adapter?.itemCount == expectedItemCount
    }

    override fun describeTo(description: Description) {
        description.appendText("recycler view does not contains $expectedItemCount items, size is $size")
    }
}