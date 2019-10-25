package com.example.eloyvitorio.githubjavapop.suiteTestEspresso

import com.example.eloyvitorio.githubjavapop.mainTestsEspresso.MainActivityEspressoTest
import com.example.eloyvitorio.githubjavapop.pullrequestTestsEspresso.PullRequestsListActivityEspressoTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainActivityEspressoTest::class,
        PullRequestsListActivityEspressoTest::class)
class InstrumentationTestsSuite