package br.com.guilherme.solution.module

import android.app.Activity
import br.com.guilherme.solution.ui.repositoryList.RepositoryListContract
import br.com.guilherme.solution.ui.repositoryList.RepositoryListPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    /*@Provides
    fun provideIssuePresenter(): IssueContract.Presenter {
        return IssuePresenter()
    }*/

    @Provides
    fun providePresenter(): RepositoryListContract.Presenter {
        return RepositoryListPresenter()
    }
}