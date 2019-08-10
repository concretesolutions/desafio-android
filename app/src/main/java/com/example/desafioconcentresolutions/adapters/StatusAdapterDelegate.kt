package com.example.desafioconcentresolutions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class StatusAdapterDelegate {

    fun onCreateViewHolder(@NonNull inflater: LayoutInflater, @NonNull parent: ViewGroup, @NonNull layoutToInflate: Int): RecyclerView.ViewHolder {
        return StatusViewHolder(
            inflater.inflate(
                layoutToInflate,
                parent,
                false
            )
        )
    }

    fun onBindViewHolder() {
        //ignore
    }

    companion object {
        const val VIEW_TYPE_STATUS = 0
    }

    data class StatusViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){}
}