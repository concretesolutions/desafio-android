package contarini.com.desafio_tembici.data.extensions

import android.app.Activity
import android.content.Intent
import android.os.Handler
import contarini.com.desafio_tembici.R

fun Activity.startActivitySlideTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.from_right_out, R.anim.from_right_in, 1, requestCode)
}

fun Activity.startActivityTransition(intent: Intent, idAnimationOut: Int,
                                     idAnimationIn: Int, delay: Long, requestCode: Int? = null) {
    if (requestCode == null) {
        Handler().postDelayed({
            this.startActivity(intent)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    } else {
        Handler().postDelayed({
            this.startActivityForResult(intent, requestCode)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    }
}

