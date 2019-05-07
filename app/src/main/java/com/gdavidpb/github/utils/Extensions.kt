package com.gdavidpb.github.utils

import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gdavidpb.github.domain.usecase.coroutines.Result
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/* Context */

fun ConnectivityManager.isNetworkAvailable() = activeNetworkInfo?.isConnected == true

/* Live data */

typealias LiveResult<T> = MutableLiveData<Result<T>>

/* LiveResult */

fun <T> LiveResult<T>.postSuccess(value: T) = postValue(Result.OnSuccess(value))
fun <T> LiveResult<T>.postThrowable(throwable: Throwable) = postValue(Result.OnError(throwable))
fun <T> LiveResult<T>.postLoading() = postValue(Result.OnLoading())
fun <T> LiveResult<T>.postCancel() = postValue(Result.OnCancel())
fun <T> LiveResult<T>.postEmpty() = postValue(Result.OnEmpty())

/* Observers */

fun <T, L : LiveData<T>> FragmentActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

/* Coroutines */

suspend fun <T> Call<T>.await() = suspendCoroutine<T?> { continuation ->
    enqueue(object : Callback<T?> {
        override fun onResponse(call: Call<T?>, response: Response<T?>) {
            if (response.isSuccessful)
                continuation.resume(response.body())
            else
                continuation.resumeWithException(HttpException(response))
        }

        override fun onFailure(call: Call<T?>, t: Throwable) {
            continuation.resumeWithException(t)
        }
    })
}

/* View */

fun TextView.drawables(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)

fun View.onClickOnce(onClick: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            view.setOnClickListener(null)

            also { listener ->
                CoroutineScope(Dispatchers.Main).launch {
                    onClick()

                    withContext(Dispatchers.IO) { delay(500) }

                    view.setOnClickListener(listener)
                }
            }
        }
    })
}

/* Parsing */

private val defaultFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
private val cacheFormat = hashMapOf<String, SimpleDateFormat>()

fun Date.format(format: String) = cacheFormat.getOrPut(format, { defaultFormat }).format(this)