package com.rafael.fernandes.desafioconcrete.ui.activities.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rafael.fernandes.desafioconcrete.ui.base.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val mutableLiveData = MutableLiveData<Boolean>()

    fun getLiveData(): LiveData<Boolean> {
        return mutableLiveData
    }

    init {
        GlobalScope.launch {
            delay(1000)
            mutableLiveData.postValue(true)
        }
    }

    override fun myOnCleared() {

    }

}