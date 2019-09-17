package com.example.desafioconcentresolutions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafioconcentresolutions.R
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        materialButton_homePageFragment_goToRepo.setOnClickListener {
            NavHostFragment.findNavController(this@HomePageFragment).navigate(R.id.action_homePageFragment_to_gitHubRepoListFragment)
        }
    }


}
