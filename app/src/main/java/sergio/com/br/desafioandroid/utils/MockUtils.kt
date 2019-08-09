package sergio.com.br.desafioandroid.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.models.PullRequestModel
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

object MockUtils {

    fun loadPullRequestList(context: Context): ArrayList<PullRequestModel> {
        val type = object : TypeToken<List<PullRequestModel>>() {

        }.type
        val json: String = readFileFromRawDirectory(context, R.raw.pull_request_mock)

        return Gson().fromJson(json, type)
    }

    private fun readFileFromRawDirectory(context: Context, resourceId: Int): String {
        val iStream = context.resources.openRawResource(resourceId)
        var byteStream: ByteArrayOutputStream? = null
        try {
            val buffer = ByteArray(iStream.available())
            iStream.read(buffer)
            byteStream = ByteArrayOutputStream()
            byteStream.write(buffer)
            byteStream.close()
            iStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return byteStream!!.toString()
    }
}