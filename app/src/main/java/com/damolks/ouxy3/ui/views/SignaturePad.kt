package com.damolks.ouxy3.ui.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.damolks.ouxy3.R
import kotlin.math.abs

class SignaturePad @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var path = Path()
    private val paths = mutableListOf<Path>()
    private val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = ContextCompat.getColor(context, R.color.md_theme_light_primary)
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 12f
    }

    private var lastX = 0f
    private var lastY = 0f
    private var dx = 0f
    private var dy = 0f
    private var curveEndX = 0f
    private var curveEndY = 0f

    private var isDrawing = false
    private var hasSignature = false
    private var onSignatureChangeListener: ((Boolean) -> Unit)? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isDrawing = true
                path = Path()
                path.moveTo(x, y)
                lastX = x
                lastY = y
                paths.add(path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                dx = abs(x - lastX)
                dy = abs(y - lastY)
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    // Calculate quadratic bezier curve points
                    curveEndX = (x + lastX) / 2
                    curveEndY = (y + lastY) / 2
                    path.quadTo(lastX, lastY, curveEndX, curveEndY)
                    lastX = x
                    lastY = y
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                isDrawing = false
                path.lineTo(lastX, lastY)
                if (!hasSignature) {
                    hasSignature = true
                    onSignatureChangeListener?.invoke(true)
                }
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paths.forEach { path ->
            canvas.drawPath(path, paint)
        }
    }

    fun clear() {
        paths.clear()
        hasSignature = false
        onSignatureChangeListener?.invoke(false)
        invalidate()
    }

    fun getSignatureBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }

    fun setOnSignatureChangeListener(listener: (Boolean) -> Unit) {
        onSignatureChangeListener = listener
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }
}