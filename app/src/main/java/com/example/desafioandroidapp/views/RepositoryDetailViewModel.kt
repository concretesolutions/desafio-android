package com.example.desafioandroidapp.views

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
    var openedPullsNumber = MutableLiveData(0)
    var closedPullsNumber = MutableLiveData(0)

    fun getPulls(owner: String, repository: String){
        success.value = false
        data.value = null

        desafioApiRepository.getPulls(object: PullListener<List<Pull>> {
                override fun onResponsePull(response: List<Pull>) {
                    if(response.isNotEmpty()) {
                        data.value = response
                        pullStatusCount(data.value!!)
                        success.value = true
                    }
                }

                override fun onError(repositoryError: Error) {
                    Toast.makeText(null, "Error getting imageSlider data", Toast.LENGTH_LONG).show()
                }

            }, owner, repository)
    }

    private fun pullStatusCount(pullsList : List<Pull>){
        pullsList.forEach {
            println("estado: ${it.state}")
            if(it.state == "open"){
                openedPullsNumber.value = openedPullsNumber.value?.plus(1)
            }else{
                closedPullsNumber.value = closedPullsNumber.value?.plus(1)
            }
        }
    }
}
