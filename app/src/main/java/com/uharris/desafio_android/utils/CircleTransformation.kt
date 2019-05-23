package com.uharris.desafio_android.utils

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

    override fun key() = "CircleTransform"

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)

        if (squaredBitmap != source)
            source.recycle()

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val paint = Paint().apply {
            isAntiAlias = true

            shader = BitmapShader(
                squaredBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }

        Canvas(bitmap).apply {
            val r = size / 2f

            drawCircle(r, r, r, paint)
        }

        squaredBitmap.recycle()

        return bitmap
    }
}