package com.germanofilho.pullrequest.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse
import com.germanofilho.pullrequest.repository.PullRequestRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: PullRequestViewModel
    private val mockedRepo = mock<PullRequestRepository>()

    private val type = object : TypeToken<List<GitPullRequestResponse>>() {}.type
    private val response = Gson().fromJson<List<GitPullRequestResponse>>(getResponse(), type)

    @Before
    fun setup_test(){
        viewModel = PullRequestViewModel(mockedRepo)
    }

    @Test
    fun given_a_venue_should_load_data() {
        val liveData = MutableLiveData<List<GitPullRequestResponse>>().apply {
            value = response
        }

        whenever(runBlocking {
            mockedRepo.getPullRequestList(any(), any())
        })
            .thenReturn(liveData.value!!)

        viewModel.pullRequestList.observeForever {
            Assert.assertEquals(it, response)
        }
    }

    private fun getResponse() =
        "[{\n" +
                "\t\t\"html_url\": \"https://github.com/facebook/litho/pull/670\",\n" +
                "\t\t\"title\": \"Migrate documentation to Docusaurus v2\",\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"login\": \"teikjun\",\n" +
                "\t\t\t\"avatar_url\": \"https://avatars3.githubusercontent.com/u/46853051?v=4\"\n" +
                "\t\t},\n" +
                "\t\t\"body\": \"<!-- Thanks for submitting a pull request! We appreciate you taking the time to work on these \\r\\nchanges. Please provide enough information so that others can review your pull request. The three \\r\\nfields below are mandatory. -->\\r\\n\\r\\n## Summary\\r\\n\\r\\n<!-- Explain the **motivation** for making this change. What existing problem does the pull request \\r\\nsolve? -->\\r\\n\\r\\nHi there! As discussed in #669, we would like to migrate Litho's documentation website to Docusaurus 2. \\r\\n\\r\\nDocusaurus 2 brings about many improvements:\\r\\n- Client-side rendering with prerendering (site renders without JavaScript!)\\r\\n- Embeddable interactive React components within markdown via MDX\\r\\n- Docusaurus sites are React apps built with modern Javascript ecosystem tooling\\r\\n\\r\\nIn this PR, we've migrated the documentation over to Docusaurus 2 (in `/website` directory) while leaving the `/docs` directory intact. \\r\\nWe've matched the design of the original site as much as possible, so that the transition will be smooth for users of Litho.\\r\\n\\r\\n## Changelog\\r\\n\\r\\n<!-- Help reviewers and the release process by writing your own changelog entry. This should just be\\r\\na brief oneline we can mention in our release notes: https://github.com/facebook/litho/releases -->\\r\\n\\r\\nMigrate documentation website from Jekyll to Docusaurus v2\\r\\n\\r\\n## Test Plan\\r\\n\\r\\n<!-- Demonstrate the code is solid. Example: The exact commands you ran and their output, output of \\r\\nthe test runner and how you invoked it (you've added tests, right?), screenshots/videos if the pull \\r\\nrequest changes UI. -->\\r\\n\\r\\nThe test site is up! https://litho-deploy.vercel.app\\r\\n\\r\\n@Drewbi and I are looking to improve the documentation site further based on your feedback. \\r\\nPlease let us know what you think! :)\\r\\n\\r\\ncc @yangshun \\r\\n\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"html_url\": \"https://github.com/facebook/litho/pull/670\",\n" +
                "\t\t\"title\": \"Migrate documentation to Docusaurus v2\",\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"login\": \"teikjun\",\n" +
                "\t\t\t\"avatar_url\": \"https://avatars3.githubusercontent.com/u/46853051?v=4\"\n" +
                "\t\t},\n" +
                "\t\t\"body\": \"<!-- Thanks for submitting a pull request! We appreciate you taking the time to work on these \\r\\nchanges. Please provide enough information so that others can review your pull request. The three \\r\\nfields below are mandatory. -->\\r\\n\\r\\n## Summary\\r\\n\\r\\n<!-- Explain the **motivation** for making this change. What existing problem does the pull request \\r\\nsolve? -->\\r\\n\\r\\nHi there! As discussed in #669, we would like to migrate Litho's documentation website to Docusaurus 2. \\r\\n\\r\\nDocusaurus 2 brings about many improvements:\\r\\n- Client-side rendering with prerendering (site renders without JavaScript!)\\r\\n- Embeddable interactive React components within markdown via MDX\\r\\n- Docusaurus sites are React apps built with modern Javascript ecosystem tooling\\r\\n\\r\\nIn this PR, we've migrated the documentation over to Docusaurus 2 (in `/website` directory) while leaving the `/docs` directory intact. \\r\\nWe've matched the design of the original site as much as possible, so that the transition will be smooth for users of Litho.\\r\\n\\r\\n## Changelog\\r\\n\\r\\n<!-- Help reviewers and the release process by writing your own changelog entry. This should just be\\r\\na brief oneline we can mention in our release notes: https://github.com/facebook/litho/releases -->\\r\\n\\r\\nMigrate documentation website from Jekyll to Docusaurus v2\\r\\n\\r\\n## Test Plan\\r\\n\\r\\n<!-- Demonstrate the code is solid. Example: The exact commands you ran and their output, output of \\r\\nthe test runner and how you invoked it (you've added tests, right?), screenshots/videos if the pull \\r\\nrequest changes UI. -->\\r\\n\\r\\nThe test site is up! https://litho-deploy.vercel.app\\r\\n\\r\\n@Drewbi and I are looking to improve the documentation site further based on your feedback. \\r\\nPlease let us know what you think! :)\\r\\n\\r\\ncc @yangshun \\r\\n\"\n" +
                "\t}\n" +
                "]"
}