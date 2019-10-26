package com.maze.prefix

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import kotlin.properties.Delegates


// This is for the prefix.
// It is a drawable for rendering text and image
internal class PrefixDrawable(
        var paint: Paint,
        val paddImage: Int = 5,//todo change to dp 
        var lineBounds: Int = 0
) : Drawable() {
    //save prefix txt
    var text: String by Delegates.observable("") { _, _: String?, _: String? ->
        // Tell it we need to be as big as we want to be!
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        invalidateSelf()
    }
    //save picture for draw
    var drawable: Drawable? by Delegates.observable(null) { _, _: Drawable?, _: Drawable? ->
        // Tell it we need to be as big as we want to be!
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        invalidateSelf()
    }


    override fun draw(canvas: Canvas) {
        text.let {
            val y = (lineBounds + canvas.clipBounds.top).toFloat()
            canvas.drawText(it, 0f, y, paint)
        }
        if (drawable != null){
            val ytext = paint.measureText(text).toInt() + paddImage
            drawable!!.setBounds(ytext, 0, intrinsicHeight + ytext, intrinsicHeight)
            drawable!!.draw(canvas)
        }
    }

    override fun setAlpha(i: Int) {
        paint.alpha = i
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun getIntrinsicHeight(): Int {
        return paint.textSize.toInt()
    }

    override fun getIntrinsicWidth(): Int {
        if (drawable !=null)
            return paint.measureText(text).toInt() + intrinsicHeight + paddImage //if drawable exit the width have to be biger
        else
            return paint.measureText(text).toInt()
    }
}
