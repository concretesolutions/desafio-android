package com.concrete.desafio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.concrete.desafio.data.GestorDeRepositorios
import com.concrete.desafio.data.Repositorio

class RepositorioViewModel(val gestorRepositorios: GestorDeRepositorios): ViewModel() {

    private var mRepositorios: MutableLiveData<MutableList<Repositorio>>? = null

    fun getRepositorios(): LiveData<MutableList<Repositorio>>? {
        if(mRepositorios == null){
            gestorRepositorios.buscarRepositorios()
            mRepositorios = gestorRepositorios.getRepositorios()
        }

        return mRepositorios
    }

}