package sergio.com.br.desafioandroid.api

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.utils.Utils
import java.io.IOException

object APIUtils {
    fun errorResponse(context: Context, response: Response<*>) {
        var errorMessage = ""
        try {
            errorMessage = JSONObject(response.errorBody().string()).getString("error")
        } catch (e: JSONException) {
            errorMessage = e.message!!
        } catch (e: IOException) {
            errorMessage = e.message!!
        }

        Utils.showSimpleMessage(context, "Code ${response.code()}", errorMessage)
    }

    fun errorResponseWithThrowable(context: Context, errorMessage: String) {
        Utils.showSimpleMessage(context, context.getString(R.string.api_error_text), errorMessage)
    }
}