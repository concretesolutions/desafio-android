package br.com.guilherme.solution

import br.com.guilherme.solution.models.Repository
import br.com.guilherme.solution.ui.repositoryList.RepositoryListPresenter
import org.junit.Test

class MainUnitTest {

    @Test
    fun mainTest() {
        val list = ArrayList<Repository>()
        assert(list.isEmpty())

        val presenter = RepositoryListPresenter()

        val data  = presenter.loadData("","", 0)
        assert(data != null)
    }
}