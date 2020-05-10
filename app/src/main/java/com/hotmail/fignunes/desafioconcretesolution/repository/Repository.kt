package com.hotmail.fignunes.desafioconcretesolution.repository

import android.content.Context
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.RemoteFactory
import com.hotmail.fignunes.skytestefabionunes.repository.remote.RemoteRepository

class Repository(applicationContext: Context) : RepositoryFactory {

    override val remote: RemoteFactory = RemoteRepository()
}