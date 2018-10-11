package com.diegoferreiracaetano.domain.pull

import androidx.paging.DataSource
import io.reactivex.Flowable
import io.reactivex.Single

interface PullRepository{
    fun getList(owner:String,repo:String,page: Int): Flowable<List<Pull>>

    fun getList(owner:String,repo:String): DataSource.Factory<Int, Pull>

    fun getTotal(): Single<Int>

    fun save(list: List<Pull>): Flowable<List<Long>>

}