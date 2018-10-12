package com.rafaelpereiraramos.desafioAndroid

import android.app.Application

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
class App : Application() {

    companion object {
        const val URL_BASE = "https://api.github.com/"
        const val DATE_FORMAT = "YYYY-MM-DDTHH:MM:SSZ"
    }
}