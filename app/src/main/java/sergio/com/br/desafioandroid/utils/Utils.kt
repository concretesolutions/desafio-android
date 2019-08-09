package sergio.com.br.desafioandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import java.text.SimpleDateFormat

object Utils {
    fun getFormatedNumber(number: Long): String {
        var formatedText: String = ""

        if (number >= 1000000) {
            formatedText = "${number / 1000000}kk"
        } else if (number >= 1000) {
            formatedText = "${number / 1000}k"
        } else {
            formatedText = "${number}"
        }

        return formatedText
    }


    fun showSimpleMessage(context: Context, titleText: String, contentText: String) {
        SweetAlertDialog(context)
            .setTitleText(titleText)
            .setContentText(contentText)
            .show()
    }

    @SuppressLint("SimpleDateFormat")
    fun formartDate(dateToBeFormated: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateToBeFormated)
        return SimpleDateFormat("dd/MM/yyyy").format(date)
    }
}