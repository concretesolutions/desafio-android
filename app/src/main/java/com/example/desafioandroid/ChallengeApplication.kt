package com.example.desafioandroid

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.desafioandroid.api.ApiInterface
import com.example.desafioandroid.api.RetrofitClient
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ChallengeApplication : MultiDexApplication() {

    var apiService: ApiInterface? = null
        get() {
            if (field == null) {
                this.apiService = RetrofitClient.create()
            }

            return field
        }
    private var scheduler: Scheduler? = null

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }

    operator fun get(context: Context): ChallengeApplication {
        return context.applicationContext as ChallengeApplication
    }


}
