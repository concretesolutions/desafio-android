package br.com.desafio_android_concrete.desafioandroidconcrete

import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.model.User
import br.com.desafio.concrete.network.ApiDataSource
import br.com.desafio.concrete.view.pullrequest.PullRequestContract
import io.reactivex.Observable
import org.junit.Test
import org.koin.standalone.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*


class PullRequestsUnitTest : BaseUnitTest() {

    val presenter by inject<PullRequestContract.Presenter> ()

    @Mock
    lateinit var view: PullRequestContract.View

    @Mock
    lateinit var apiDataSource: ApiDataSource


    override fun before() {
        super.before()
        presenter.attachView(view)
    }

    @Test
    fun fetchPullRequests(){

        `when`(apiDataSource.fetchPullRequests(getMockRepo())).thenReturn(Observable.just(getMockPullRequest()))

        `when`(presenter.fetchPullRequests(getMockRepo())).then{
            verify(view).showLoadingIndicator(false)
            verify(view).onPullRequestLoaded(getMockPullRequest())
            val throwable = Mockito.mock(Throwable::class.java)
            verify(view, never()).showUnexpectedError(throwable)
            verify(view, never()).showEmptyState()
        }
    }

    @Test
    fun fetchEmptyPullRequest() {
        val emptyList = ArrayList<PullRequest>()

        `when`(apiDataSource.fetchPullRequests(getMockRepo())).thenReturn(Observable.just(emptyList))

        `when`(presenter.fetchPullRequests(getMockRepo())).then {
            verify(view).showLoadingIndicator(false)
            verify(view, only()).showEmptyState()

            verify(view, never()).onPullRequestLoaded(getMockPullRequest())
            val throwable = Mockito.mock(Throwable::class.java)
            verify(view, never()).showUnexpectedError(throwable)
            verify(view, never()).onPullRequestLoaded(getMockPullRequest())
        }






    }

    private fun getMockRepo(): Repository{
        val owner = User("user_login", "avatar_url")
        return Repository("any_name", "any_full_name", "any_description", owner, 2487, 5487)
    }


    private fun getMockPullRequest(): ArrayList<PullRequest>{
        val user = User("any_login", "any_avatar_url")
        val pull1 = PullRequest("any_url", "any_title", user, "any_date", "any_body", "any_state")
        val pull2 = PullRequest("any_url2", "any_title2", user, "any_date2", "any_body2", "any_state2")


        val list = ArrayList<PullRequest>()
        list.add(pull1)
        list.add(pull2)

        return list
    }
}
