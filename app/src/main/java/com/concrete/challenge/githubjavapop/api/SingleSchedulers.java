package com.concrete.challenge.githubjavapop.api;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface SingleSchedulers {
    SingleSchedulers INSTANCE = new SingleSchedulers() {
        @Override
        public <T> SingleTransformer<T, T> applySchedulers() {
            return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    SingleSchedulers TEST = new SingleSchedulers() {
        @Override
        public <T> SingleTransformer<T, T> applySchedulers() {
            return upstream -> upstream.subscribeOn(Schedulers.trampoline()).observeOn(Schedulers.trampoline());
        }
    };

    <T> SingleTransformer<T, T> applySchedulers();
}
