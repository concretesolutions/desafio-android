package com.ruiderson.desafio_android.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScroll : RecyclerView.OnScrollListener {

    private var recyclerView: RecyclerView
    private var layoutManager: LinearLayoutManager
    private var percentage = 0
    private var currentPosition: Position = Position.START

    abstract fun onFirstItem()
    abstract fun onScroll()
    abstract fun onLoadMore()



    constructor(recyclerView: RecyclerView, loadPercentage: Int){
        this.percentage = loadPercentage
        this.recyclerView = recyclerView
        this.layoutManager = (recyclerView.layoutManager as LinearLayoutManager)

        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                //Check if reached the first item
                val firstItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                if(firstItem == 0){
                    if(currentPosition != Position.START){
                        onFirstItem()
                    }
                    currentPosition = Position.START
                    return
                }


                //Check if reached the load more position
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val lastItem = layoutManager.findLastVisibleItemPosition()
                val currentPercentage = ((totalItemCount * percentage) / 100)
                if(lastItem >= currentPercentage){
                    if(currentPosition != Position.LOAD_MORE){
                        onLoadMore()
                    }

                    currentPosition = Position.LOAD_MORE
                    return
                }


                //Scrolling
                if(currentPosition != Position.LOAD_MORE){
                    onScroll()
                }
                currentPosition = Position.SCROLLING


            }
        })
    }



    private enum class Position(val value: Int) {
        START(1),
        SCROLLING(2),
        LOAD_MORE(3)
    }
}