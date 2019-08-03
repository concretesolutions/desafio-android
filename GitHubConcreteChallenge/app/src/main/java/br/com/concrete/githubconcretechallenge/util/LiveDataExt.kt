package br.com.concrete.githubconcretechallenge.util

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<*>.forceSetValue() {
    this.value = this.value
}