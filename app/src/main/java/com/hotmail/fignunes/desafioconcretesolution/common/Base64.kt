package com.hotmail.fignunes.desafioconcretesolution.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream

class Base64 {

    companion object {

        fun imageviewToString(imageView: ImageView): String {
            try {
                imageView.buildDrawingCache()
                val bitmap = imageView.getDrawingCache()

                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val imageByte = stream.toByteArray()

                return Base64.encodeToString(imageByte, 0)
            } catch (e: Exception) {
                return ""
            }
        }

        fun stringToBitmap(image: String): Bitmap {
            val decodedString = Base64.decode(image, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }
    }
}