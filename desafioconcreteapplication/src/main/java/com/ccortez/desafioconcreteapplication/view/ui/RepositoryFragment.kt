package com.ccortez.desafioconcreteapplication.view.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Pair
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccortez.desafioconcreteapplication.R
import com.ccortez.desafioconcreteapplication.databinding.FragmentPullListBinding
import com.ccortez.desafioconcreteapplication.di.Injectable
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.repository.BackEndService
import com.ccortez.desafioconcreteapplication.view.adapter.RepositoryPullsAdapter
import com.ccortez.desafioconcreteapplication.view.callback.RepositoryPullsItemClickCallback
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject

class RepositoryFragment : Fragment(), Injectable {

    private lateinit var repositoryPullsAdapter: RepositoryPullsAdapter
    private lateinit var binding: FragmentPullListBinding
    var mActionMode: ActionMode? = null
//    var db: AppDatabase? = null
    var backendService: BackEndService? = null

    var viewModelFactory: ViewModelProvider.Factory? = null
        @Inject set

    var qtdList: MutableList<Pair<String, String>>? = null
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_list, container, false)
        repositoryPullsAdapter = RepositoryPullsAdapter(repositoryPullsClickCallback)
        binding!!.pullsItems.adapter = repositoryPullsAdapter
        mActionMode =
            activity?.startActionMode(mActionModeCallback)
        mContext = activity?.applicationContext
//        db = getAppDatabase(mContext?.getApplicationContext())
//        backendService = BackEndService(mContext)
        // Create and set the adapter for the RecyclerView.
        return binding.getRoot()
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

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RepositoryViewModel::class.java)

        viewModel.setCarID(arguments!!.getString(KEY_CAR_ID))
//        binding!!.carViewModel = viewModel
        binding!!.isLoading = true
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: RepositoryViewModel) { // Observe car data
        viewModel.observableCar.observe(this, Observer { car ->
            if (car != null) {
                binding!!.isLoading = false
                binding!!.tvEmptyPullList.text = ""
                repositoryPullsAdapter!!.setItems(car)
                if (repositoryPullsAdapter!!.pullsItems!!.isEmpty()) {
                    binding!!.tvEmptyPullList.text = getString(R.string.empty_pulls)
                }
//                viewModel.setCar(car)
//                Picasso.get()
//                    .load(car.imagem)
//                    .placeholder(R.drawable.ic_car)
//                    .error(R.drawable.ic_alert)
//                    .into(binding!!.imageView)
//                binding!!.btnColocarCarrinho.setOnClickListener {
//                    Log.d(
//                        TAG,
//                        "QTD SELECTED: " + binding!!.spinnerQtd.selectedItem
//                    )
//
//                }
//                addItemsOnSpinner(binding!!.spinnerQtd, car.quantidade)
            }
        })
    }

    fun addItemsOnSpinner(spin: Spinner, qtd: Int) {
        mContext = activity?.applicationContext
        //        db = AppDatabase.getAppDatabase(mContext.getApplicationContext());
//        spin = view.findViewById(R.id.spinner_gerir);
        val list: MutableList<String> =
            ArrayList()
        qtdList = ArrayList()
        for (i in 1 until qtd + 1) {
            (qtdList as ArrayList<Pair<String, String>>).add(Pair(i.toString(), i.toString()))
            //            eventsList.add(new Pair<>(lista_enderecos.get(i).getNomeDivulgacao(), lista_enderecos.get(i).getCodigo()));
//            list.add(lista_enderecos.get(i).getNomeDivulgacao());
            list.add(i.toString())
        }
        val dataAdapter = ArrayAdapter(
            activity!!.applicationContext,
            android.R.layout.simple_spinner_item,
            list
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = dataAdapter
    }

    private val mActionModeCallback: ActionMode.Callback =
        object : ActionMode.Callback {
            override fun onCreateActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean { // Inflate a menu resource providing context menu items
                val inflater = mode.menuInflater
                for (i in 0 until menu.size()) {
                    menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
                }
                inflater.inflate(R.menu.menu_no_items, menu)
                return true
            }

            override fun onPrepareActionMode(
                mode: ActionMode,
                menu: Menu
            ): Boolean {
                return true
            }

            override fun onActionItemClicked(
                mode: ActionMode,
                item: MenuItem
            ): Boolean { // ver onPositiveClick para ação de cada um
                return when (item.itemId) {
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                mActionMode = null
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    (activity as MainActivity?)!!.showHome()
                }
            }
        }

    private val repositoryPullsClickCallback: RepositoryPullsItemClickCallback = object :
        RepositoryPullsItemClickCallback {
        override fun onClick(item: PullRequest) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//                (activity as MainActivity).show(item)
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url))
                startActivity(browserIntent)
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
        const val TAG = "RepositoryFragment"
        private const val KEY_CAR_ID = "full_name"
        /**
         * Creates car fragment for specific car ID
         */
        fun forCar(full_name: String?): RepositoryFragment {
            val fragment = RepositoryFragment()
            val args = Bundle()
            args.putString(KEY_CAR_ID, full_name)
            fragment.arguments = args
            return fragment
        }
    }
}