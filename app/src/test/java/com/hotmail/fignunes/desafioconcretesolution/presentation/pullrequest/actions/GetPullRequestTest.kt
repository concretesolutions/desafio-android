package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.actions

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
class GetPullRequestTest {

    private lateinit var applicationContext: Context
    private lateinit var repository: Repository

    @Before
    fun before() {
        applicationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        repository = Repository(applicationContext)
    }

    @Test
    fun `should return list of gitrepositories with 修复失效的链接0`() {
        val login = "CyC2018"
        val repositoryPr = "CS-Notes"
        val title = "修复失效的链接"

        val pulRequests = GetPullRequest(repository).execute(login, repositoryPr).blockingGet()
        val result = pulRequests[0].title
        Assert.assertEquals(title, result)
    }
}