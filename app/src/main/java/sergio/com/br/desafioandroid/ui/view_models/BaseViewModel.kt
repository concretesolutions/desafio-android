package sergio.com.br.desafioandroid.ui.view_models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    var hasLoaded: Boolean = false

    val onError: MutableLiveData<Response<*>> by lazy {
        MutableLiveData<Response<*>>()
    }

    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val onThrowable: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}