package com.danielmaia.desafio_android.listeners;

public interface ListListener {
    void onListSuccess(boolean hasMore);
    void onListError(String message);
}
