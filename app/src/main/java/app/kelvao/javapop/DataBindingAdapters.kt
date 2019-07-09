package app.kelvao.javapop

import android.widget.TextView
import androidx.databinding.BindingAdapter

class DataBindingAdapters {

    @BindingAdapter("android:text")
    fun setText(textView: TextView, value: Long) {
        textView.text = value.toString()
    }

}