package com.example.desafioandroidapp.views

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroidapp.data.DesafioApiRepository
import com.example.desafioandroidapp.data.RepositoryListener
import com.example.desafioandroidapp.data.dto.RepositoryItem
import com.example.desafioandroidapp.data.dto.Repository
import com.example.desafioandroidapp.data.dto.Error

class RepositoryMainViewModel(private val desafioApiRepository: DesafioApiRepository) : ViewModel(){
    val success = MutableLiveData(false)
    val data = MutableLiveData<List<RepositoryItem>?>(null)
    val continueapi = MutableLiveData(true)

    fun getRepositories(page: Int) {
        success.value = false
        data.value = null

        desafioApiRepository.getRepositories(object: RepositoryListener<Repository> {
            override fun onResponse(response: Repository) {
                if(response.total_count > 0) {
                    data.value = response.repositoryItems
                    continueapi.value = response.incomplete_results
                    success.value = true
                }
            }

            override fun onError(repositoryError: Error) {
                Toast.makeText(null, "Error getting imageSlider data", Toast.LENGTH_LONG).show()
            }

        }, page)
    }
}