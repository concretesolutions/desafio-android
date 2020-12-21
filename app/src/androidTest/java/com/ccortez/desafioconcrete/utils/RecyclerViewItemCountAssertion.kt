package com.ccortez.desafioconcrete.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.ccortez.desafioconcrete.model.Repositories
import junit.framework.TestCase

class RecyclerViewItemCountAssertion
     : ViewAssertion {

    val expectedCount: Int?

    constructor(expectedCount: Int)
    {
        this.expectedCount = expectedCount;
    }

    private val carList: List<Repositories>? = null

    override fun check(
        view: View,
        noViewFoundException: NoMatchingViewException?
    ) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        //        RecyclerView.Adapter adapter = (ShopCartAdapter) recyclerView.getAdapter();
        val adapter
                = recyclerView.adapter
        //        ((ShopCartAdapter) adapter).setCarList(carList);
        println("adapter.getItemCount(): " + adapter!!.itemCount)
        //        assertThat(adapter.getItemCount(), is(expectedCount));
        TestCase.assertEquals(expectedCount, adapter.itemCount)
    }

}