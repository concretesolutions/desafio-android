package com.example.rafaelanastacioalves.moby.util;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelperMethods {

    public static Matcher<RecyclerView.ViewHolder> withHolderContainingId(final int id) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(RecyclerView.ViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder item) {
                View visualizeView = item.itemView.findViewById(id);
                if (visualizeView == null) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with id: " + id);
            }

        };
    }

    public static void waitForAdapterChange(final RecyclerView recyclerView) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                recyclerView.getAdapter().registerAdapterDataObserver(
                        new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onItemRangeInserted(int positionStart, int itemCount) {
                                latch.countDown();
                            }

                            @Override
                            public void onChanged() {
                                latch.countDown();
                            }
                        });
            }
        });
        if (recyclerView.getAdapter().getItemCount() > 0) {
            return;//already loaded
        }
        assertThat(latch.await(10, TimeUnit.SECONDS), is(true));
    }
}
