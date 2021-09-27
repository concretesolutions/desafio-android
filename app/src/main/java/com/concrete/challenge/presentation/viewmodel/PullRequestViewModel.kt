package com.concrete.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PullRequestViewModel() : ViewModel() {

    val pullRequestsList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
}