package br.com.githubrepos.util;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

public class IdleResourceHandler {

    //0 - idle
    //1 or more - busy
    private static final String RESOURCE = "GLOBAL";
    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    //to became busy
    public static void increment() {
        mCountingIdlingResource.increment();
    }

    //to became idle
    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }


    static class SimpleCountingIdlingResource implements IdlingResource {

        private final String mResourceName;
        private final AtomicInteger counter = new AtomicInteger(0);

        // written from main thread, read from any thread.
        private volatile ResourceCallback resourceCallback;

        /**
         * Creates a SimpleCountingIdlingResource
         *
         * @param resourceName the resource name this resource should report to Espresso.
         */
        public SimpleCountingIdlingResource(String resourceName) {
            mResourceName = resourceName;
        }

        @Override
        public String getName() {
            return mResourceName;
        }

        @Override
        public boolean isIdleNow() {
            return counter.get() == 0;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }

        /**
         * Increments the count of in-flight transactions to the resource being monitored.
         */
        public void increment() {
            counter.getAndIncrement();
        }

        /**
         * Decrements the count of in-flight transactions to the resource being monitored.
         * If this operation results in the counter falling below 0 - an exception is raised.
         *
         * @throws IllegalStateException if the counter is below 0.
         */
        public void decrement() {
            int counterVal = counter.decrementAndGet();
            if (counterVal == 0) {
                // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
                if (null != resourceCallback) {
                    resourceCallback.onTransitionToIdle();
                }
            } else if (counterVal < 0) {
                throw new IllegalStateException("Counter has been corrupted!");
            }
        }
    }
}
