package dev.renatoneto.githubrepos.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.renatoneto.githubrepos.R
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException

abstract class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    val loading = MutableLiveData<Boolean>()

    val error = MutableLiveData<Int>()

    fun showError(throwable: Throwable) {

        throwable.printStackTrace()

        if (throwable is IOException) {
            error.value = R.string.error_connection
            error.postValue(R.string.error_connection)
        } else {
            error.postValue(R.string.error_unexpected)
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}