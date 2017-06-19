package br.com.concrete.desafio.transform

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader.TileMode.CLAMP
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

    override fun key() = "circle"

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap !== source) source.recycle()

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, CLAMP, CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val radius = size / 2f
        canvas.drawCircle(radius, radius, radius, paint)

        squaredBitmap.recycle()
        return bitmap
    }
}