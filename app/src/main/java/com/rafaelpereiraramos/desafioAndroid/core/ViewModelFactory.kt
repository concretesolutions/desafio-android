package com.rafaelpereiraramos.desafioAndroid.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Singleton
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]

        if (creator == null) {
            for ((viewModelClass, providerOfViewModel) in creators) {
                if (modelClass.isAssignableFrom(viewModelClass)) {
                    creator = providerOfViewModel;
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException(
                    String.format("%s isn't binded in ViewModelModule", modelClass.canonicalName))
        }

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}