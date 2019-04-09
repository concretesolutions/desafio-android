package com.example.desafioandroid.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R

class PullFragment : Fragment() {
    val TAG = javaClass.name
    var idRepository: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_pull, container, false )
        idRepository = arguments!!.getString(getString(R.string.bundle_id_repository))
        return view
    }
}