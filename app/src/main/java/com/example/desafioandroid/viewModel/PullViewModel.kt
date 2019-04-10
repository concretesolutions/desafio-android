package com.example.desafioandroid.viewModel

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.example.desafioandroid.ChallengeApplication
import com.example.desafioandroid.R
import com.example.desafioandroid.api.ApiInterface
import com.example.desafioandroid.schemas.PullItem
import com.example.desafioandroid.schemas.RepositoryItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

class PullViewModel(private var context: FragmentActivity?, private var creator: String, private var nameRepository:String): Observable() {
    var pullProcess: ObservableInt = ObservableInt(View.GONE)
    var recyclerPull: ObservableInt = ObservableInt(View.GONE)
    var labelStatus: ObservableInt = ObservableInt(View.VISIBLE)
    var messageLabel: ObservableField<String> = ObservableField(context!!.getString(R.string.app_name))

    private lateinit var pullList : List<PullItem>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()


    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }


    fun initializeViews() {
        labelStatus.set(View.GONE)
        recyclerPull.set(View.GONE)
        pullProcess.set(View.VISIBLE)
        fetchPullList()
    }

    private fun fetchPullList() {
        val challengeApplication = ChallengeApplication()[context!!.baseContext]
        val apiService: ApiInterface = challengeApplication.apiService!!

        val disposable : Disposable = apiService.getPull(creator,nameRepository)
            .subscribeOn(challengeApplication.subscribeScheduler())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                changeRepositoryDataSet(it)
                pullProcess.set(View.GONE)
                labelStatus.set(View.GONE)
                recyclerPull.set(View.VISIBLE)
            }, {
                messageLabel.set(context!!.getString(R.string.app_name))
                pullProcess.set(View.GONE)
                labelStatus.set(View.VISIBLE)
                recyclerPull.set(View.GONE)
            })

        compositeDisposable!!.add(disposable)
    }


    private fun changeRepositoryDataSet(responsePullList: List<PullItem>) {
        pullList = responsePullList
        this.setChanged()
        notifyObservers(pullList)
    }


    fun getPullList(): List<PullItem> {
        return pullList
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
    }
}
