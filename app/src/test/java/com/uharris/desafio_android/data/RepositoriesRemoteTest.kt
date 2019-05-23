package com.uharris.desafio_android.data

import com.uharris.desafio_android.data.remote.RepositoriesRemoteImpl
import com.uharris.desafio_android.data.services.RepositoriesServices
import com.uharris.desafio_android.presentation.base.NetworkHandler
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoriesRemoteTest {

    @Mock
    private lateinit var service: RepositoriesServices
    @Mock
    private lateinit var networkHandler: NetworkHandler

    private lateinit var repositoriesRemote: RepositoriesRemote

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        repositoriesRemote = RepositoriesRemoteImpl(networkHandler, service)
    }


}