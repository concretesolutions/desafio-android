package com.example.desafioandroid.domain

import com.example.desafioandroid.data.ReposRepository
import com.example.desafioandroid.data.model.OwnerModel
import com.example.desafioandroid.domain.model.Repo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetReposTest {

    @RelaxedMockK
    private lateinit var repository: ReposRepository

    lateinit var getRepos: GetRepos

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRepos = GetRepos(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { repository.searchRepositoriesApi("q", 30) } returns emptyList()

        //When
        getRepos("q", 30)

        //Then
        coVerify(exactly = 1) { repository.searchRepositoriesDb() }
    }

    @Test
    fun `when the api return something get value from database`() = runBlocking {
        //Given
        val myList = listOf(
            Repo(
                276963,
                "jQuery-Validation-Engine",
                "jQuery form validation plugin",
                "posabsolute/jQuery-Validation-Engine",
                OwnerModel(
                    114950,
                    "https://avatars.githubusercontent.com/u/114950?v=4",
                    "posabsolute"
                ),
                2590,
                1232
            )
        )
        coEvery { repository.searchRepositoriesApi("q", 30) } returns myList
        //When
        val response = getRepos("q", 30)
        //Then
        coVerify(exactly = 1) { repository.deleteAllRepos() }
        coVerify(exactly = 1) { repository.insertRepos(any()) }
        coVerify(exactly = 0) { repository.searchRepositoriesDb() }
        assert(response == myList)
    }
}