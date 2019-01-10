package br.com.appdesafio.task;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorsBackground implements Executor {

    private final Executor mDiskIO;

    public ExecutorsBackground() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(final @NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
