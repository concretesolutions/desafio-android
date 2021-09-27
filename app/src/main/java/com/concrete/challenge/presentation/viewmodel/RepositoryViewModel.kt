package com.concrete.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepositoryViewModel() : ViewModel() {

    val repositoriesList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

}