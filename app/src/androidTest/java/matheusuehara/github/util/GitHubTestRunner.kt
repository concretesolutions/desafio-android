package matheusuehara.github.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import matheusuehara.github.ApplicationTest

class GitHubTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(
                cl,
                ApplicationTest::class.java.name,
                context)
    }
}