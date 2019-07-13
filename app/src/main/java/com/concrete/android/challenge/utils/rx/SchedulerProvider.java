package com.concrete.android.challenge.utils.rx;

import io.reactivex.Scheduler;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public interface SchedulerProvider {

  Scheduler ui();

  Scheduler computation();

  Scheduler io();

}
