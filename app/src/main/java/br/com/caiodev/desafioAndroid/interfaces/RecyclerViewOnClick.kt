package br.com.caiodev.desafioAndroid.interfaces

import android.view.View

interface RecyclerViewOnClick<in T> {

    fun onItemClickListener(view: View, position: Int, source: T?)
}