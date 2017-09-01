package br.com.concrete.desafio.feature

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.extension.addErrorSource

open class BaseViewModel : ViewModel() {

    val errorLiveData = MediatorLiveData<Throwable>()

    fun handleErrorFor(vararg liveData: ResponseLiveData<*>) {
        liveData.map(ResponseLiveData<*>::errorLiveData).forEach(errorLiveData::addErrorSource)
    }

}