package com.example.eloyvitorio.githubjavapop.suiteTestUnit

import com.example.eloyvitorio.githubjavapop.mainTestsUnit.MainActivityUnitTest
import com.example.eloyvitorio.githubjavapop.pullrequestTestsUnit.PullRequestListUnitTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainActivityUnitTest::class,
        PullRequestListUnitTest::class)
class UnitTestsSuite