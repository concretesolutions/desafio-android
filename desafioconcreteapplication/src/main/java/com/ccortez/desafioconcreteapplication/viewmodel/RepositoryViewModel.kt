package com.ccortez.desafioconcreteapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.ccortez.desafioconcreteapplication.service.model.PullRequest
import com.ccortez.desafioconcreteapplication.service.model.Repositories
import com.ccortez.desafioconcreteapplication.service.repository.GitHubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    carRepository: GitHubRepository,
    application: Application
) : AndroidViewModel(application) {

    lateinit var observableCar: LiveData<List<PullRequest>>
    lateinit var carID: MutableLiveData<String>
    @JvmField
    var car = ObservableField<Repositories>()

    fun setCar(car: Repositories) {
        this.car.set(car)
    }

    fun setCarID(carID: String?) {
        this.carID.value = carID
    }

    companion object {
        private val TAG = RepositoryViewModel::class.java.name
        private val ABSENT: MutableLiveData<*> = MutableLiveData<Any?>()
    }

    init {
        ABSENT.value = null
    }

    init {
        viewModelScope.launch {
            carID = MutableLiveData()
            observableCar = Transformations.switchMap<String, List<PullRequest>>(
                carID
            ) { input: String ->
                if (input.isEmpty()) {
                    Log.i(
                        TAG,
                        "CarViewModel carID is absent!!!"
                    )
//                return@switchMap ABSENT
                }
                Log.i(
                    TAG,
                    "CarViewModel carID is " + carID.value
                )
                getCarDetails(carRepository)
            }
        }
    }

    fun getCarDetails(carRepository: GitHubRepository) : LiveData<List<PullRequest>?> {
        return carRepository.getPulls(carID.value)
    }
}