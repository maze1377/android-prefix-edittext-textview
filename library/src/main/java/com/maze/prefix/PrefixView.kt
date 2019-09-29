package com.maze.prefix

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.maze.library.R


class PrefixView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet,
        defStyle: Int = 0
) : AppCompatTextView(context, attrs) {


    private val textPaint: TextPaint by lazy {
        TextPaint().apply {
            color = currentHintTextColor
            textAlign = Paint.Align.LEFT
            isAntiAlias = true
            this.typeface = typeface
        }
    }

    private val prefixDrawable: PrefixDrawable by lazy { PrefixDrawable(paint) }

    var prefix: String = ""
        set(value) {
            if (value.isNotBlank()) {
                Log.v(TAG, "prefix: $value")
            }
            field = value
            prefixDrawable.text = value
            updatePrefixDrawable()
        }
    var drawable: Drawable? = null
        set(value) {
            field = value
            prefixDrawable.drawable = value
            updatePrefixDrawable()
        }
    // These are used to store details obtained from the EditText's rendering process
    private val firstLineBounds = Rect()

    private var isInitialized = false

    init {
        textPaint.textSize = textSize
        updatePrefixDrawable()
        isInitialized = true
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.PrefixText)
        prefix = typedArray.getString(R.styleable.PrefixText_prefix) ?: ""
        val attributeResourceId = typedArray.getResourceId(R.styleable.PrefixText_res, -1)
        try  {
            drawable = ResourcesCompat.getDrawable(resources, attributeResourceId, null)
        }catch (e:Exception){

        }
        typedArray.recycle()
        layoutDirection = View.LAYOUT_DIRECTION_RTL
    }
    override fun setTypeface(tf: Typeface?) {
        super.setTypeface(typeface)
        if (isInitialized) {
            // this is first called from the constructor when it's not initialized, yet
            textPaint.typeface = typeface
        }
        postInvalidate()
    }

    public override fun onDraw(c: Canvas) {
        textPaint.color = currentHintTextColor
        val lineBounds = getLineBounds(0, firstLineBounds)
        prefixDrawable.let {
            it.lineBounds = lineBounds
            it.paint = textPaint
        }
        super.onDraw(c)
    }

    private fun updatePrefixDrawable() {
        setCompoundDrawablesRelative(prefixDrawable, null, null, null)
    }
    companion object {
        private const val TAG = "PrefixTextView"
    }
}
