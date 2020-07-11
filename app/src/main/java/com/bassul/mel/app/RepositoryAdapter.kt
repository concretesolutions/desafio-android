package com.bassul.mel.app

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.databinding.LayoutRepositoryItemBinding

class RepositoryAdapter (var items : List<Item>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    open class ViewHolder(private val binding: LayoutRepositoryItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}


