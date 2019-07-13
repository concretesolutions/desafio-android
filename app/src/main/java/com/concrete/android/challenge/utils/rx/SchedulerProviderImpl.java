package com.concrete.android.challenge.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class SchedulerProviderImpl implements SchedulerProvider {
  @Override public Scheduler io() {
    return Schedulers.io();
  }

  @Override public Scheduler computation() {
    return Schedulers.computation();
  }

  @Override public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}