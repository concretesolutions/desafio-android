package com.example.desafioandroidapp.views

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroidapp.data.DesafioApiRepository
import com.example.desafioandroidapp.data.ResponseListener
import com.example.desafioandroidapp.data.dto.Item
import com.example.desafioandroidapp.data.dto.Repository
import com.example.desafioandroidapp.data.dto.RepositoryError

class MainActivityViewModel(val desafioApiRepository: DesafioApiRepository) : ViewModel(){
    val success = MutableLiveData(false)
    val data = MutableLiveData<List<Item>?>(null)

    fun getRepos() {
        success.value = false
        data.value = null

        desafioApiRepository.getRepositories(object: ResponseListener<Repository> {
            override fun onResponse(response: Repository) {
                if(response.total_count > 0) {
                    data.value = response.items
                    success.value = true
                }
            }

            override fun onError(repositoryError: RepositoryError) {
                Toast.makeText(null, "Error getting imageSlider data", Toast.LENGTH_LONG).show()
            }

        })
    }
}