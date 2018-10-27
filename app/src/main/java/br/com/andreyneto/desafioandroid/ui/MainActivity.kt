package br.com.andreyneto.desafioandroid.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.contracts.Contract
import br.com.andreyneto.desafioandroid.dao.AppDatabase

class MainActivity : AppCompatActivity(), Contract.view {

    private lateinit var mPresenter: Contract.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }
}
