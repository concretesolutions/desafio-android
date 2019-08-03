package br.com.concrete.githubconcretechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.concrete.githubconcretechallenge.features.repositories.model.UserModel
import org.junit.Rule
import org.koin.test.KoinTest
import org.mockito.Mockito

open class BaseTest : KoinTest {

    @get:Rule
    val rxTestSchedulersRule = RxTestSchedulersRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    fun getUserModelMock() : UserModel {
        val userModel = Mockito.mock(UserModel::class.java)
        Mockito.`when`(userModel.login).thenReturn("iluwatar")

        return userModel
    }
}