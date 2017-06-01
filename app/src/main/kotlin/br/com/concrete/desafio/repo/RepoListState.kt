package br.com.concrete.desafio.repo

import android.support.annotation.IntDef

const val LIST_STATE = 0
const val LOADING_STATE = 1
const val EMPTY_STATE = 2
const val ERROR_STATE = 3

@Retention(AnnotationRetention.SOURCE)
@IntDef(LIST_STATE.toLong(), LOADING_STATE.toLong(), EMPTY_STATE.toLong(), ERROR_STATE.toLong())
annotation class RepoListState