package com.concrete.challenge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concrete.challenge.databinding.FragmentPullRequestBinding

class PullRequestFragment : Fragment() {

    private lateinit var binding: FragmentPullRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

}