package br.com.concrete.sdk.model.type

import android.support.annotation.IntDef

@IntDef(LOADING, SUCCESS, ERROR)
@Retention(AnnotationRetention.SOURCE)
annotation class DataResultStatus