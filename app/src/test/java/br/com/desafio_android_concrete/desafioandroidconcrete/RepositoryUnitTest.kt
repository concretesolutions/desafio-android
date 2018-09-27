package br.com.desafio_android_concrete.desafioandroidconcrete

import br.com.desafio.concrete.model.GitHubResponse
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.model.User
import br.com.desafio.concrete.network.ApiDataSource
import br.com.desafio.concrete.view.repository.RepositoryListContract
import io.reactivex.Observable
import org.junit.Test
import org.koin.standalone.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*


class RepositoryUnitTest : BaseUnitTest() {

    val presenter by inject<RepositoryListContract.Presenter> ()

    @Mock
    lateinit var view: RepositoryListContract.View

    @Mock
    lateinit var apiDataSource: ApiDataSource


    override fun before() {
        super.before()
        presenter.attachView(view)
    }

    @Test
    fun fetchRepositories(){

        `when`(apiDataSource.fetchRepositories("java", 1)).thenReturn(Observable.just(getMockResponse()))

        `when`(presenter.fetchRepositories("java")).then{
            verify(view).showLoadingIndicator(false)
            verify(view).onRepositoriesLoaded(getMockResponse().items)
            val throwable = Mockito.mock(Throwable::class.java)
            verify(view, never()).showUnexpectedError(throwable)
            verify(view, never()).showPaginateError(true)
        }

    }

    private fun getMockResponse(): GitHubResponse{
        val owner = User("user_login", "avatar_url")
        val repository = Repository("any_name", "any_full_name", "any_description", owner, 2487, 5487)
        val items = ArrayList<Repository>()
        items.add(repository)

        return GitHubResponse(1223,items)
    }
}
