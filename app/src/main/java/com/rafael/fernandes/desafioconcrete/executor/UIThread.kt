package com.rafael.fernandes.desafioconcrete.executor

import com.rafael.fernandes.domain.PostExecutionThread
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread @Inject constructor() : PostExecutionThread {
    override fun getScheduler(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }
}