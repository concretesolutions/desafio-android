package com.jmc.desafioandroidkotlin.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.usecase.GetPullsUseCase
import com.jmc.desafioandroidkotlin.utils.LiveResult

open class PullsViewModel(
    private val getPullsUseCase: GetPullsUseCase
) : ViewModel() {
    val pulls = LiveResult<List<PullModel>>()

    fun getPulls(repository: String) {
        getPullsUseCase.execute(liveData = pulls, params = repository)
    }
}