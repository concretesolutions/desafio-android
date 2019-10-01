package contarini.com.desafio_android.data.extensions

import android.app.Fragment
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startFragment(fragment: Fragment,
                                    allowStateLoss: Boolean = false,
                                    @IdRes containerViewId: Int) {
    val ft = fragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}