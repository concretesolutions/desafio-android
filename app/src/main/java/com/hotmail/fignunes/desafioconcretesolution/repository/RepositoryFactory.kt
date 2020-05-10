package com.hotmail.fignunes.desafioconcretesolution.repository

import com.hotmail.fignunes.desafioconcretesolution.repository.remote.RemoteFactory

interface RepositoryFactory {
    val remote: RemoteFactory
}