package com.danielmaia.desafio_android.listeners;

public interface PullListener {
    void onPullSuccess();
    void onPullError(String message);
}