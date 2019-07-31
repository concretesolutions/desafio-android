package app.kelvao.javapop

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(textView: TextView, value: Long) {
        textView.text = value.toString()
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

}