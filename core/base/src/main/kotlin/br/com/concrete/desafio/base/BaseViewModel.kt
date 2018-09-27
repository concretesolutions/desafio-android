package br.com.concrete.desafio.base

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.desafio.data.livedata.ResponseLiveData
import br.com.concrete.desafio.data.extension.addErrorSource

open class BaseViewModel : ViewModel() {

    val errorLiveData = MediatorLiveData<Throwable>()

    fun handleErrorFor(vararg liveData: ResponseLiveData<*>) {
        liveData.map(ResponseLiveData<*>::errorLiveData).forEach(errorLiveData::addErrorSource)
    }

}