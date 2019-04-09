package com.example.desafioandroid.viewModel

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.support.v4.app.FragmentActivity
import android.view.View
import com.example.desafioandroid.ChallengeApplication
import com.example.desafioandroid.R
import com.example.desafioandroid.api.ApiInterface
import com.example.desafioandroid.schemas.RepositoryItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

class RepositoryViewModel(private var context: FragmentActivity?): Observable(){

    var repositoryProgress: ObservableInt = ObservableInt(View.GONE)
    var repositoryRecycler: ObservableInt = ObservableInt(View.GONE)
    var repositoryLabel: ObservableInt = ObservableInt(View.VISIBLE)
    var messageLabel: ObservableField<String> = ObservableField(context!!.getString(R.string.app_name))

    lateinit var repositoryList : List<RepositoryItem>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun initializeViews() {
        repositoryLabel.set(View.GONE)
        repositoryRecycler.set(View.GONE)
        repositoryProgress.set(View.VISIBLE)
        fetchPeopleList()
    }

    private fun fetchPeopleList() {
        val challengeApplication = ChallengeApplication()[context!!.baseContext]
        val apiService: ApiInterface = challengeApplication.apiService!!

        val disposable : Disposable = apiService.getRepositories("java","stars",1)
            .subscribeOn(challengeApplication.subscribeScheduler())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                changeRepositoryDataSet(it.items!!)
                repositoryProgress.set(View.GONE)
                repositoryLabel.set(View.GONE)
                repositoryRecycler.set(View.VISIBLE)
                //notifyObservers()

            }, {
                messageLabel.set(context!!.getString(R.string.app_name))
                repositoryProgress.set(View.GONE)
                repositoryLabel.set(View.VISIBLE)
                repositoryRecycler.set(View.GONE)
            })

        compositeDisposable!!.add(disposable)
    }

    private fun changeRepositoryDataSet(responseRepositoryList: List<RepositoryItem>) {
        repositoryList = responseRepositoryList
        this.setChanged()
        notifyObservers(repositoryList)
    }


    fun getPeopleList(): List<RepositoryItem> {
        return repositoryList
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
    }
}