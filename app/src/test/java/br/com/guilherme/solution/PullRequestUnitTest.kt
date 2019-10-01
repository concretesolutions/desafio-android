package br.com.guilherme.solution

import br.com.guilherme.solution.models.Repository
import br.com.guilherme.solution.ui.pullRequests.PullRequestsPresenter
import org.junit.Test

class PullRequestUnitTest {

    @Test
    fun mainTest() {
        val list = ArrayList<Repository>()
        assert(list.isEmpty())

        val presenter = PullRequestsPresenter()

        val data = presenter.loadData("", "")
        assert(data != null)
    }
}