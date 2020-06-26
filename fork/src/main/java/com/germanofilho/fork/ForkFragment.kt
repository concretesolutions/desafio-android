package com.germanofilho.fork

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.germanofilho.core.ui.BaseFragment

class ForkFragment : BaseFragment(R.layout.fragment_fork){

    val args: ForkFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("ARGS", args.fullName)

        //findNavController().navigate()
    }
}