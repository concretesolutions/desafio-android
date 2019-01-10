package br.com.appdesafio.task;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private final Executor diskIoThread;

    private final Executor networkIoThread;

    private final Executor mainThread;

    public AppExecutors(final Executor diskIO, final Executor networkIO, final Executor mainThread) {
        this.diskIoThread = diskIO;
        this.networkIoThread = networkIO;
        this.mainThread = mainThread;
    }

    public Executor diskIO() {
        return diskIoThread;
    }

    public Executor networkIO() {
        return networkIoThread;
    }

    public Executor mainThread() {
        return mainThread;
    }


}
