package contarini.com.desafio_tembici.data.extensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber


fun <T> Single<T>.singleSubscribe(onSuccess: ((t: T) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        onSuccess?.let { it(t) }
                    }

                    override fun onError(e: Throwable) {
                        onError?.let { it(e) }
                    }
                })

fun <T> Observable<T>.observableSubscribe(onComplete: (() -> Unit)? = null, onNext: ((t: T) -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableObserver<T>() {
                    override fun onNext(t: T) {
                        onNext?.let { it(t) }
                    }

                    override fun onComplete() {
                        onComplete?.let { it() }
                    }

                    override fun onError(e: Throwable) {
                        onError?.let { it(e) }
                    }
                })


fun Completable.completableSubscribe(onComplete: (() -> Unit)? = null, onError: ((e: Throwable) -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        onComplete?.let { it() }
                    }

                    override fun onError(e: Throwable) {
                        onError?.let { it(e) }
                    }
                })


fun <T> Flowable<T>.flowableSubscribe(onNext: ((t: T) -> Unit)? = null, onError: ((e: Throwable?) -> Unit)? = null,
                                      onComplete: (() -> Unit)? = null, subscribeOnScheduler: Scheduler? = Schedulers.io(), observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()) =
        this.subscribeOn(subscribeOnScheduler!!)
                .observeOn(observeOnScheduler)
                .subscribeWith(object : DisposableSubscriber<T>() {
                    override fun onError(t: Throwable?) {
                        onError?.let { it(t) }
                    }

                    override fun onComplete() {
                        onComplete?.let { it() }
                    }

                    override fun onNext(t: T) {
                        onNext?.let { it(t) }
                    }
                })

