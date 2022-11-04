package com.example.desafioandroidapp.views

import android.content.ClipData
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroidapp.data.DesafioApiRepository
import com.example.desafioandroidapp.data.PullListener
import com.example.desafioandroidapp.data.dto.Error
import com.example.desafioandroidapp.data.dto.Pull

class RepositoryDetailViewModel (private val desafioApiRepository: DesafioApiRepository) : ViewModel(){
    val success = MutableLiveData(false)
    val data = MutableLiveData<List<Pull>?>(null)

    fun getPulls(owner: String, repository: String){
        success.value = false
        data.value = null

        desafioApiRepository.getPulls(object: PullListener<List<Pull>> {
                override fun onResponsePull(response: List<Pull>) {
                    if(response.size > 0) {
                        data.value = response
                        success.value = true
                    }
                    System.out.println("Size: ${data.value?.size}")
                }

                override fun onError(repositoryError: Error) {
                    Toast.makeText(null, "Error getting imageSlider data", Toast.LENGTH_LONG).show()
                }

            }, owner, repository)
    }
}
