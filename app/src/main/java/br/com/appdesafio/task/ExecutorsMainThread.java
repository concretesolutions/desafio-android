package br.com.appdesafio.task;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class ExecutorsMainThread implements Executor {
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(final @NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}

