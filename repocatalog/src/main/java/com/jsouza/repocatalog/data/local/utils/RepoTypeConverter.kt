package com.jsouza.repocatalog.data.local.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jsouza.repocatalog.data.repocatalog.remote.response.Owner

class RepoTypeConverter {

    companion object {
        private var gson = Gson()

        @TypeConverter
        fun fromOwner(owner: Owner?): String? {
            if (owner == null) { return null }
            gson = Gson()
            val type = object : TypeToken<Owner>() {
            }.type

            return gson.toJson(owner, type)
        }

        @TypeConverter
        fun toOwner(pokeVarieties: String?): Owner? {
            if (pokeVarieties == null) { return null }
            gson = Gson()
            val type = object : TypeToken<Owner>() {
            }.type

            return gson.fromJson(pokeVarieties, type)
        }
    }
}
