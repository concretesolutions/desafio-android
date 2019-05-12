package com.gdavidpb.github.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.usecase.GetPullsUseCase
import com.gdavidpb.github.utils.LiveResult

open class PullsViewModel(
    private val getPullsUseCase: GetPullsUseCase
) : ViewModel() {
    val pulls = LiveResult<List<Pull>>()

    fun getPulls(repository: String) {
        getPullsUseCase.enqueue(liveData = pulls, params = repository)
    }
}