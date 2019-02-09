package com.accenture.desafioandroid.utils

import android.content.Context

class MySharedPreferences(context: Context?) {

    val PREFERENCE_NAME = "SharedPreferences"

    val PREFERENCE_OWNER = "owner"
    val PREFERENCE_REPO = "repo"


    val preference = context?.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)


    fun getOwner(): String {

        return preference!!.getString(PREFERENCE_OWNER, "")

    }

    fun setOwner(owner: String) {

        val editor = preference?.edit()
        editor?.putString(PREFERENCE_OWNER, owner)
        editor?.apply()
    }

    fun getRepo(): String {

        return preference!!.getString(PREFERENCE_REPO, "")

    }

    fun setRepo(repo: String) {

        val editor = preference?.edit()
        editor?.putString(PREFERENCE_REPO, repo)
        editor?.apply()
    }




    fun clearAll(){

    }
}










