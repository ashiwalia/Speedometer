package me.ashish.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View


/**
 * Created by ashishmac on 06/10/17.
 */
class PathExample : View {

    val path = Path()
    val paint = Paint()
    var position: Float = 30f


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun init() {
        path.moveTo(0f, 0f)



        paint.strokeWidth = 130f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("MYTAG", " PathExample onDraw Called")

        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()

        path.reset()
        path.addArc((width / 2 - width / 3),
                (height / 2 - width / 3),
                (width / 2 + width / 3),
                (height / 2 + width / 3), 150f, position)
        canvas.drawPath(path, paint)
        position -= 1


        //   invalidate()

    }


}