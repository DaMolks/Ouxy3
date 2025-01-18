package com.damolks.ouxy3.ui.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.withSave

class SignaturePad @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var path = Path()
    private val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 12f
    }

    private var currentX = 0f
    private var currentY = 0f
    private val dirtyRect = RectF()
    private val paths = mutableListOf<Path>()

    init {
        paths.add(path)
    }

    override fun onDraw(canvas: Canvas) {
        paths.forEach { path ->
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(eventX, eventY)
                currentX = eventX
                currentY = eventY
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = Math.abs(eventX - currentX)
                val dy = Math.abs(eventY - currentY)

                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    path.quadTo(
                        currentX,
                        currentY,
                        (eventX + currentX) / 2,
                        (eventY + currentY) / 2
                    )
                    currentX = eventX
                    currentY = eventY

                    dirtyRect.left = currentX.coerceAtMost(eventX) - HALF_STROKE_WIDTH
                    dirtyRect.right = currentX.coerceAtLeast(eventX) + HALF_STROKE_WIDTH
                    dirtyRect.top = currentY.coerceAtMost(eventY) - HALF_STROKE_WIDTH
                    dirtyRect.bottom = currentY.coerceAtLeast(eventY) + HALF_STROKE_WIDTH

                    postInvalidateOnAnimation(
                        dirtyRect.left.toInt(),
                        dirtyRect.top.toInt(),
                        dirtyRect.right.toInt(),
                        dirtyRect.bottom.toInt()
                    )
                }
            }
            MotionEvent.ACTION_UP -> {
                path = Path()
                paths.add(path)
            }
        }
        return true
    }

    fun clear() {
        paths.clear()
        path = Path()
        paths.add(path)
        postInvalidateOnAnimation()
    }

    fun isEmpty(): Boolean = paths.size <= 1

    fun toBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        canvas.withSave {
            draw(this)
        }
        return bitmap
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
        private const val HALF_STROKE_WIDTH = 6f
    }
}