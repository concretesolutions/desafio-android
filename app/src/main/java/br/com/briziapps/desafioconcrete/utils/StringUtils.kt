package br.com.briziapps.desafioconcrete.utils

import android.content.Context
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class StringUtils {

    companion object{

        fun formataDataHora(data: String, context: Context): String {

            val sdf = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            var date: Date? = null
            try {
                date = sdf.parse(data)
            } catch (e: ParseException) {
                Log.i("StringUtils", "formataDataHora  error : ${e.message}")
            }

            val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
            return dateFormat.format(date)
        }

    }

}