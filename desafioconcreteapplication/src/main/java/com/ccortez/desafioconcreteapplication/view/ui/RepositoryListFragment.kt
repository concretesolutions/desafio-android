package com.ccortez.desafioconcreteapplication.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.FragmentRepositoryListBinding
import com.ccortez.desafioconcreteapplication.di.Injectable
import com.ccortez.desafioconcreteapplication.service.model.Items
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.view.adapter.RepositoryAdapter
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryClickCallback
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RepositoryListFragment : Fragment(), Injectable {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var binding: FragmentRepositoryListBinding
    var mContext: Context? = null
//    var repositoryService: RepositoryService? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_list, container, false)
        repositoryAdapter = RepositoryAdapter(repositoryClickCallback)
        binding!!.carList.adapter = repositoryAdapter
        binding!!.setIsLoading(true)
        mContext = activity!!.getApplicationContext()
//        repositoryService = RepositoryService(mContext)
        binding!!.fab.setOnClickListener {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//                (activity as MainActivity?)!!.showCart()
            }
        }
        return binding!!.getRoot()
    }

    override fun onAttach(context: Context) {
        // you should create a `DaggerAppComponent` instance once, e.g. in a custom `Application` class and use it throughout all activities and fragments
//        (context.applicationContext as MVVMApplication).appComponent.inject(this)
//        DaggerAppComponent.builder()
//            .application(context.applicationContext as MVVMApplication)
//            .build().inject(this)
//        (context.applicationContext as MVVMApplication)
//            .hotelListFragmentInjector(this)
        AndroidSupportInjection.inject(this)
//        DaggerAppComponent.builder()
//            .application(context.applicationContext as MVVMApplication).build()
//            .inject()
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(RepositoryListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryListViewModel) { // Update the list when the data changes
        viewModel.repositoriesObservable
            .observe(this, Observer { repositories ->
                if (repositories != null) {
                    binding!!.isLoading = false
//                    repositoryAdapter!!.setCarList(cars)
                    repositoryAdapter!!.setRepositories(repositories)
                }
            })
    }

    private val repositoryClickCallback: RepositoryClickCallback = object : RepositoryClickCallback {
        override fun onClick(item: Items) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(item)
            }
        }

//        override fun onClickPutInCart(car: Repositories) {
//            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//                val carToVerify = db!!.carDao()?.getCarById(car.id)
//                if (carToVerify == null) {
//                    if (car.preco + carService!!.sumFromShopCart <= 100000) {
//                        car.quantidade = 1
//                        db!!.carDao()?.insert(car)
//                        Toast.makeText(
//                            activity!!.applicationContext,
//                            "Adicionado ao carrinho !",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    } else Toast.makeText(
//                        activity!!.applicationContext,
//                        "Carrinho passará de 100.000 em compras !",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else Toast.makeText(
//                    activity!!.applicationContext,
//                    "Carro já no carrinho !",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }

    companion object {
        const val TAG = "RepositoryListFragment"
    }
}