package br.com.concrete.sdk.model.type

import android.support.annotation.StringDef

@StringDef(OPEN, CLOSED, ALL)
@Retention(AnnotationRetention.SOURCE)
annotation class State