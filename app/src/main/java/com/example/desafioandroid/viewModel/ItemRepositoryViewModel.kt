package com.example.desafioandroid.viewModel

import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import com.example.desafioandroid.schemas.RepositoryItem
import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.os.Bundle
import android.provider.Settings.Global.getString
import de.hdodenhof.circleimageview.CircleImageView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.desafioandroid.R
import com.example.desafioandroid.view.PullFragment
import com.example.desafioandroid.view.RepositoryFragment


class ItemRepositoryViewModel(var mRepositoryItem: RepositoryItem, val fragmentManager: FragmentManager): BaseObservable() {
    val TAG = javaClass.simpleName

    fun onItemClick(view: View) {
        val bundle = Bundle()
        bundle.putString(getString(R.string.bundle_amount), amount)

        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = PullFragment()
        fragmentTransaction.replace(R.id.fragment_container, repositoryFragment)
            .addToBackStack(RepositoryFragment().TAG)
            .commit()
    }



   companion object{
       @JvmStatic
       @BindingAdapter("imageUrl")
       fun loadPicture(view: CircleImageView, loadPicture: String) {
           Glide.with(view.context).load(loadPicture).into(view)
       }
   }

    fun cantFork(): String{
        return mRepositoryItem.forksCount.toString()
    }

    fun cantWatcher(): String{
        return mRepositoryItem.watchersCount.toString()
    }

    fun cantLike():String{
        return mRepositoryItem.stargazersCount.toString()
    }

}