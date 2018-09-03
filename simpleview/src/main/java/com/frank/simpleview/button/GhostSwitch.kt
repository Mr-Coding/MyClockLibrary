package com.frank.simpleview.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.Button
import com.frank.simpleview.R

class GhostSwitch:Button{
    private var circular   = 30f
    private var isSelect   = false
    private var selectColor = Color.parseColor("#ffffff")
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
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.GhostSwitch)
        circular = typedArray.getFloat(R.styleable.GhostSwitch_circular,30f)
        isSelect = typedArray.getBoolean(R.styleable.GhostSwitch_isSelect,false)
        typedArray.recycle()
    }

    private fun init(){
        paint = Paint()
        paint.color = selectColor
        paint.alpha = 100
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.isAntiAlias = true

        rectF = RectF()

        setTextColor(color)
        setBackgroundColor(Color.TRANSPARENT)

        setOnClickListener {
            isSelect = !isSelect
            setSelect()
        }

    }

    fun setSelect():Boolean{
        if (isSelect){
            paint.alpha = 255
            setTextColor(selectColor)
        }else{
            paint.alpha = 100
            setTextColor(color)
        }
        postInvalidate()
        return isSelect
    }

    override fun onDraw(canvas: Canvas?) {
        if (isSelect){
            paint.alpha = 255
            setTextColor(selectColor)
        }else{
            paint.alpha = 100
            setTextColor(color)
        }

        rectF.set(5f,5f,width.toFloat()-5,height.toFloat()-5)

        canvas?.drawRoundRect(rectF,circular,circular,paint)

        super.onDraw(canvas)
    }
}