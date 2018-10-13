package com.rafaelpereiraramos.desafioAndroid.di

import javax.inject.Qualifier

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Named(val value: String)