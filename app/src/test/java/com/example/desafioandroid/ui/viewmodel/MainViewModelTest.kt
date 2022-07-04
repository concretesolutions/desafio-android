package com.example.desafioandroid.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafioandroid.data.model.OwnerModel
import com.example.desafioandroid.data.model.PullModel
import com.example.desafioandroid.domain.GetPullsByOwner
import com.example.desafioandroid.domain.GetRepos
import com.example.desafioandroid.domain.model.Repo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @RelaxedMockK
    private lateinit var getRepos: GetRepos

    @RelaxedMockK
    private lateinit var getPullByOwner: GetPullsByOwner

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getRepos, getPullByOwner)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getRepos return a repo set on the livedata`() = runTest {
        //Given
        val repo = listOf(
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
        coEvery {getRepos("q", 30) } returns repo

        //When
        mainViewModel.loadRepositories("q", 30)

        //Then
        assert(mainViewModel.repositoriesModel.value == repo)
    }

    @Test
    fun `when getPullByOwner return a pulls set on the livedata`() = runTest {
        //Given
        val pull = listOf(
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
        coEvery { getPullByOwner("octocat", "Hello-World") } returns pull

        //When
        mainViewModel.loadPullOwner("octocat", "Hello-World")

        //Then
        assert(mainViewModel.pullModel.value == pull)
    }
}
