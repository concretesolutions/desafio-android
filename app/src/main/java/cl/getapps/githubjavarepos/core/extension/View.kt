/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cl.getapps.githubjavarepos.core.extension

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrl(url: String) =
    Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)

/*fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: FragmentActivity) {
    val target: Target<Drawable> = ImageViewBaseTarget(
        this,
        activity
    )
    Picasso.get().load(url).into(target)
}

private class ImageViewBaseTarget(var imageView: ImageView?, var activity: FragmentActivity?) : BaseTarget<Drawable>() {
    override fun removeCallback(cb: SizeReadyCallback?) {
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
        imageView?.setImageDrawable(resource)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}*/
