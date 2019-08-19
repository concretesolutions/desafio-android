package dev.theuzfaleiro.trendingongithub.runner

import android.app.Application
import android.content.Context
import dev.theuzfaleiro.trendingongithub.app.TrendingOnGitHubTestApplication
import io.appflate.restmock.android.RESTMockTestRunner

class TrendingOnGitHubRunner : RESTMockTestRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application =
        super.newApplication(cl, TrendingOnGitHubTestApplication::class.java.canonicalName, context)
}