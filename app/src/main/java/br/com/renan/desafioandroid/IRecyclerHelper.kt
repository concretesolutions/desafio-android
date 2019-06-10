package br.com.renan.desafioandroid

import android.os.Bundle
import android.widget.AdapterView

abstract class IRecyclerHelper {

    var listener: AdapterView.OnItemClickListener? = null

    interface OnItemClickListner {
        fun onItemClick(bundle: Bundle)
    }
}