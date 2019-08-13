package com.rafael.fernandes.data.executor;


import androidx.annotation.NonNull;
import com.rafael.fernandes.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Singleton
public class JobExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    JobExecutor() {

        threadPoolExecutor = new ThreadPoolExecutor(
                3, // coreThreadPoolSize,
                5, // maximumThreadPoolSize,
                10L, // Timeout
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }
}