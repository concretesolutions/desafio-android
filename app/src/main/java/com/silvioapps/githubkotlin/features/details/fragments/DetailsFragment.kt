package com.silvioapps.githubkotlin.features.details.fragments

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvioapps.githubkotlin.R
import com.silvioapps.githubkotlin.databinding.FragmentDetailsBinding
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.shared.fragments.CustomFragment

class DetailsFragment : CustomFragment(){
    private var fragmentDetailsBinding : FragmentDetailsBinding? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(layoutInflater : LayoutInflater, viewGroup : ViewGroup?, bundle : Bundle?) : View? {
        fragmentDetailsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, viewGroup, false)
        fragmentDetailsBinding?.listModel = arguments?.getSerializable("details") as ListModel
        return fragmentDetailsBinding?.root
    }
}
