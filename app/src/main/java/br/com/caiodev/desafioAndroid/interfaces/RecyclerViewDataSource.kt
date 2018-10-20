package br.com.caiodev.desafioAndroid.interfaces

interface RecyclerViewDataSource<out T> {

    fun getTotalCount(): Int
    fun getViewType(position: Int): Int
    fun getItem(position: Int): T
}