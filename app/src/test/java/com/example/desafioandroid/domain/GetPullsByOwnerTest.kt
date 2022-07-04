package com.example.desafioandroid.domain

import android.util.Log
import com.example.desafioandroid.data.PullRepository
import com.example.desafioandroid.data.model.OwnerModel
import com.example.desafioandroid.data.model.PullModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPullsByOwnerTest {

    @RelaxedMockK
    private lateinit var repository: PullRepository

    lateinit var getPullsByOwner: GetPullsByOwner

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPullsByOwner = GetPullsByOwner(repository)
    }

    @Test
    fun `when the api responds with the pull requests`() = runBlocking {
        //Given
        val myList = listOf(
            PullModel(
                963655909,
                "Update",
                "Hello world",
                2298,
                "2022-06-22T11:52:36Z",
                "https://github.com/octocat/Hello-World/pull/2298",
                OwnerModel(
                    106979158, "https://avatars.githubusercontent.com/u/106979158?v=4",
                    "NinjaGuy128Vietnam"
                )
            )
        )
        coEvery { repository.getPullByOwner("octocat", "Hello-World") } returns myList
        //When
        val response = getPullsByOwner("octocat", "Hello-World")!!

        //Then
        coVerify(exactly = 1) { repository.getPullByOwner("octocat", "Hello-World") }
        assert(response == myList)
    }
}