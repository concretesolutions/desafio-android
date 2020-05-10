package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository

import com.hotmail.fignunes.skytestefabionunes.model.GitRepository

interface GitRepositoryContract {
    fun initializeList(gitRepository: GitRepository)
    fun initializeAdapter(gitRepository: GitRepository)
    fun message(error: Int)
    fun initializeSwipe()
    fun initializeScroolListener()
    fun swipeOff()
    fun setFinishLoading()
}