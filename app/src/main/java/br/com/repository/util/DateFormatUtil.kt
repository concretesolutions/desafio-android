package br.com.repository.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {

    @JvmStatic
    fun dateFormat(date: String): String {
        val dDate: Date?
        var dataFormat: String

        try {
            dDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("pt", "br")).parse(date)
            dataFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "br")).format(dDate)
        } catch (e: ParseException) {
            dataFormat = date
            e.printStackTrace()
        }

        return dataFormat
    }

}