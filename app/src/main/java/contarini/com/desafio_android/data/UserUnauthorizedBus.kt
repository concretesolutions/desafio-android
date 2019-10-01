package contarini.com.desafio_tembici.data

import io.reactivex.subjects.PublishSubject

object UserUnauthorizedBus {

    val subject = PublishSubject.create<Any>()

    fun setEvent(error: Any) {
        subject.onNext(error)
    }
}