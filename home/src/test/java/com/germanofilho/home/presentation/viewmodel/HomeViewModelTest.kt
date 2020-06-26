package com.germanofilho.home.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.germanofilho.home.repository.HomeRepository
import com.germanofilho.network.feature.repositorylist.model.entity.GitRepositoryResponse
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest{
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel
    private val mockedRepo = mock<HomeRepository>()

    private val response = Gson().fromJson(getResponse(), GitRepositoryResponse::class.java)

    @Before
    fun setup_test(){
        viewModel = HomeViewModel(mockedRepo)
    }

    @Test
    fun given_a_venue_should_load_data() {
        val liveData = MutableLiveData<GitRepositoryResponse>().apply {
            value = response
        }

        whenever(runBlocking {
            mockedRepo.getRepositoryList()
        })
            .thenReturn(liveData.value)

        viewModel.repositoryList.observeForever {
            Assert.assertEquals(it, response)
        }
    }

    private fun getResponse() =
        "{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"name\": \"litho\",\n" +
                "      \"full_name\": \"facebook/litho\",\n" +
                "      \"owner\": {\n" +
                "        \"login\": \"facebook\",\n" +
                "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/69631?v=4\"\n" +
                "      },\n" +
                "      \"description\": \"A declarative framework for building efficient UIs on Android.\",\n" +
                "      \"stargazers_count\": 6758,\n" +
                "      \"forks_count\": 613\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ShortcutBadger\",\n" +
                "      \"full_name\": \"leolin310148/ShortcutBadger\",\n" +
                "      \"owner\": {\n" +
                "        \"login\": \"leolin310148\",\n" +
                "        \"avatar_url\": \"https://avatars1.githubusercontent.com/u/5039811?v=4\"\n" +
                "      },\n" +
                "      \"description\": \"An Android library supports badge notification like iOS in Samsung, LG, Sony and HTC launchers.\",\n" +
                "      \"stargazers_count\": 6680,\n" +
                "      \"forks_count\": 1231\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"SmartTabLayout\",\n" +
                "      \"full_name\": \"ogaclejapan/SmartTabLayout\",\n" +
                "      \"owner\": {\n" +
                "        \"login\": \"ogaclejapan\",\n" +
                "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1496485?v=4\"\n" +
                "      },\n" +
                "      \"description\": \"A custom ViewPager title strip which gives continuous feedback to the user when scrolling\",\n" +
                "      \"stargazers_count\": 6663,\n" +
                "      \"forks_count\": 1338\n" +
                "    }\n" +
                "  ]\n" +
                "}"
}
