package com.example.consultor.testacc.presentation.datasources

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.example.consultor.testacc.data.pojos.Repository


class SimpleDataSourceFactory : DataSource.Factory<Int, Repository>() {
    //este data source factory envia la data gradualmente
    var itemLiveDS=MutableLiveData<PageKeyedDataSource<Int,Repository>>()
    override fun create(): DataSource<Int, Repository> {
       var itemdataSource=SimpleDataSource()
        itemLiveDS.postValue(itemdataSource)
        return itemdataSource
    }


}