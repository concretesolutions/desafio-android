package br.com.eriberto.desafioandroidconcrete.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio

class RepositorioViewModel : ViewModel() {

    var listaDeRepositorios = MutableLiveData<ArrayList<Repositorio>>().apply { value = arrayListOf() }

    fun updateListaRepositorios(listaDeRepositorios: MutableLiveData<ArrayList<Repositorio>>) {

    }

    private fun update(score: MutableLiveData<Int>){
        val value = score.value ?:0
        score.value = value+1
    }
}