package dev.renatoneto.githubrepos.util

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object MockUtils {

    fun readFileFromAssets(context: Context?, fileName: String): String {
        if (context == null) return ""

        val builder = StringBuilder()
        try {
            val stream = context.assets.open(fileName)
            val bReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var line: String? = bReader.readLine()

            while (line != null) {
                builder.append(line)
                line = bReader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return builder.toString().substring(0)
    }

}