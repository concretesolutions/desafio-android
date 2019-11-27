package com.haldny.githubapp.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.util.HumanReadables

class RecyclerItemViewAssertion<A>(
    private val position: Int,
    private val item: A,
    private val assertion: (item: A, view: View, e: NoMatchingViewException?) -> Unit
) : ViewAssertion {

    override fun check(view: View, e: NoMatchingViewException?) {
        val recyclerView = view as RecyclerView
        val viewHolderForPosition = recyclerView.findViewHolderForLayoutPosition(position)
        if (viewHolderForPosition == null) {
            throw PerformException.Builder()
                .withActionDescription(toString())
                .withViewDescription(HumanReadables.describe(view))
                .withCause(IllegalStateException("No view holder at position: $position"))
                .build()
        } else {
            val viewAtPosition = viewHolderForPosition.itemView
            assertion(item, viewAtPosition, e)
        }
    }
}