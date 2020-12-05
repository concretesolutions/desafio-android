package com.ccortez.desafioconcreteapplication.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.service.model.Items
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Add car list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = RepositoryListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, RepositoryListFragment.TAG).commit()
        }
    }

    fun show(car: Items) {
        val carFragment = RepositoryFragment.forCar(car.owner!!.login+"/"+car.name.toString())
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("item")
            .replace(
                R.id.fragment_container,
                carFragment, RepositoryFragment.TAG
            ).commit()
    }

    fun showHome() {
        val carListFragment = RepositoryListFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("repositorylist")
            .replace(
                R.id.fragment_container,
                carListFragment, RepositoryListFragment.TAG
            ).commit()
    }

//    fun showCart() {
//        val shopCartListFragment = ShopCartListFragment()
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("shopcart")
//            .replace(
//                R.id.fragment_container,
//                shopCartListFragment, ShopCartListFragment.TAG
//            ).commit()
//    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

}