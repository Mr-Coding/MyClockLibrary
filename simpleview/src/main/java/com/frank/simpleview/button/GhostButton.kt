package com.frank.simpleview.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import com.frank.simpleview.R

class GhostButton:Button{
    private var circular    = 30f
    private var pressColor = Color.parseColor("#ffffff")
    private var color = Color.parseColor("#8cffffff")

    private lateinit var paint:Paint
    private lateinit var rectF: RectF

    constructor(context: Context?) : super(context){ init() }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initAttrs(attrs)
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initAttrs(attrs)
        init()
    }

    private fun initAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.GhostButton)
        circular   = typedArray.getFloat(R.styleable.GhostButton_circular,30f)
        typedArray.recycle()
    }

    private fun init(){
        paint = Paint()
        paint.color = pressColor
        paint.alpha = 100
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.isAntiAlias = true

        rectF = RectF()

        setTextColor(color)
        setBackgroundColor(Color.TRANSPARENT)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                paint.alpha = 255
                setTextColor(pressColor)
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                paint.alpha = 100
                setTextColor(color)
            }
        }
        postInvalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        rectF.set(5f,5f,width.toFloat()-5,height.toFloat()-5)

        canvas?.drawRoundRect(rectF,circular,circular,paint)

        super.onDraw(canvas)
    }
}