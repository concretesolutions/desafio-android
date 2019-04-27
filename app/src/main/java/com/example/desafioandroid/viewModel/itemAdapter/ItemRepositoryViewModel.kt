package com.example.desafioandroid.viewModel.itemAdapter

import android.view.View
import com.example.desafioandroid.schema.RepositoryItem
import com.bumptech.glide.Glide
import android.os.Bundle
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView
import com.example.desafioandroid.R
import com.example.desafioandroid.view.fragment.PullFragment
import com.example.desafioandroid.view.fragment.RepositoryFragment


class ItemRepositoryViewModel(var mRepositoryItem: RepositoryItem, private val fragmentManager: FragmentManager): BaseObservable() {
    val TAG = javaClass.simpleName

    fun onItemClick(view: View) {
        val bundle = Bundle()
        bundle.putString(view.context.getString(R.string.bundle_id_creator), mRepositoryItem.owner!!.login)
        bundle.putString(view.context.getString(R.string.bundle_name_repository), mRepositoryItem.name)

        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = PullFragment()
        repositoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment_container,repositoryFragment)
            .addToBackStack(RepositoryFragment().TAG)
            .commit()
    }

   companion object{
       @JvmStatic
       @BindingAdapter("imageUrl")
       fun loadPicture(view: CircleImageView, loadPicture: String) {
           Glide.with(view.context)
               .load(loadPicture)
               .placeholder(R.drawable.ic_load_image)
               .error(R.drawable.ic_error_image)
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(view)
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