package com.concrete.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.challenge.data.UserEntity
import com.concrete.challenge.domain.io.APIService
import com.concrete.challenge.utils.callServiceUser

const val USER_NAME_TEST = "Snailclimb"

class UserViewModel(private val service: APIService) : ViewModel() {

    val userResponse: MutableLiveData<List<UserEntity>> by lazy {
        MutableLiveData<List<UserEntity>>()
    }

    fun getUser() {
        callServiceUser(liveData = userResponse) { service.getUser(USER_NAME_TEST) }
    }

}