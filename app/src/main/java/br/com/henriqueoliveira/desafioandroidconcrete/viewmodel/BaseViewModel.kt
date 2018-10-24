package br.com.henriqueoliveira.desafioandroidconcrete.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.Resource

@Suppress("PropertyName")
open class BaseViewModel<T> : ViewModel() {

    protected val _state: MutableLiveData<Resource<T>> = MutableLiveData()
    val state = _state as LiveData<Resource<T>>
    var isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }
}