package com.concrete.desafioandroid.features.repos

import android.util.Log
import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.Repo
import com.concrete.desafioandroid.data.source.datasource.DataSource
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher
import java.util.*
import kotlin.collections.ArrayList

class ReposInteractor(private val reposRepository: DataSource) {

    fun getReposList(queries: HashMap<String, String>,
                     onGetReposListSuccess: (list: List<Repo>) -> Unit): Disposable {
        return reposRepository.getReposList(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onGetReposListSuccess(it.items)
                        },
                        { it.printStackTrace() },
                        { Log.d("ONCOMPLETE-REPOS", "OnComplete-Repos")}
                )

    }

    fun fetchOwnerDetails(ownerUrl: String, onGetOwnerDetailsSuccess: (details: OwnerDetails) -> Unit): Disposable {
            return reposRepository.getOwnerDetails(ownerUrl)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                onGetOwnerDetailsSuccess(it)
                            },
                            { it.printStackTrace() },
                            { Log.d("ONCOMPLETE-OWNER", "OnComplete-Owner-Details")}
                    )
    }

}
