package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.actions

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.hotmail.fignunes.desafioconcretesolution.repository.Repository
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.TestApp
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class, sdk = [Build.VERSION_CODES.P])
class GetGitRepositoriesTest {

    private lateinit var applicationContext: Context
    private lateinit var repository: Repository

    @Before
    fun before() {
        applicationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        repository = Repository(applicationContext)
    }

    @Test
    fun `should return list of gitrepositories with CS-Notes`() {
        val language = "language:java"
        val stars = "stars"
        val page = 1
        val name = "CS-Notes"

        val gitRepository = GetGitRepositories(repository).execute(language, stars, page).blockingGet()
        val result = gitRepository.items[0].name
        Assert.assertEquals(name, result)
    }
}