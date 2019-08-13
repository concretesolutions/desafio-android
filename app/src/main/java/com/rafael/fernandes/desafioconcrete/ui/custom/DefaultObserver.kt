package com.rafael.fernandes.desafioconcrete.ui.custom


import android.util.Log
import io.reactivex.observers.DisposableObserver

import java.io.PrintWriter
import java.io.StringWriter

/**
 * Default [DisposableObserver] base class to be used whenever you want default error handling.
 */

open class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {
        // no-op by default.
    }

    override fun onComplete() {
        // no-op by default.
    }

    override fun onError(exception: Throwable) {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        exception.printStackTrace(pw)
        val sStackTrace = sw.toString() // stack trace as a string

        Log.e("DefaultObserver", sStackTrace)
    }
}