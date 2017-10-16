package me.ashish.mycustomview

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.graphics.Shader.TileMode
import android.graphics.LinearGradient
import android.graphics.Shader




/**
 * Created by ashishmac on 02/10/17.
 */
class Speedometer : ViewGroup {
    companion object {
        val LABEL_POS_LEFT = 0
        val LABEL_POS_RIGHT = 1

    }

    private var mShowText: Boolean = false
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }
    private var mLabelPosition: Int = LABEL_POS_LEFT
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

//    public var testValue: Float = 0f
//    set(value) {
//        pathExample.position = value
//        pathExample.invalidate()
//        arrowView.rotation = value
//    }

    val paint: Paint = Paint()
    lateinit var pathExample: PathExample
    lateinit var arrowView: ArrowView


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        pathExample = PathExample(context)
        addView(pathExample)
        arrowView = ArrowView(context)
        addView(arrowView)
        setWillNotDraw(false)
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }



    fun setTestValue(value: Float){

        pathExample.position = value
        pathExample.invalidate()
        arrowView.rotation = value
    }


    fun init(attrs: AttributeSet) {

        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.Speedometer)


        mShowText = typeArray.getBoolean(R.styleable.Speedometer_showText, false)
        mLabelPosition = typeArray.getInteger(R.styleable.Speedometer_labelPosition, 0)

        typeArray.recycle()

        paint.color = Color.RED

    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("MYTAG", "WIDTH = " + w.toString() + "HEIGHT = " +h.toString() + "OLD WIDTH = " +oldw.toString() + "OLD HEIGHT = " +oldh.toString())
        pathExample.layout(0, 0, w, h)
        //arrowView.layout((w / 2 - (w / 3 + 60)), h / 2 - 100, w / 2 + (w/ 3 + 60), h / 2 + 100)
        arrowView.layout(10, h / 2 - 100, w, h / 2 + 100)

//        val animation = AnimatorInflater.loadAnimator(context, R.animator.rotate)
//        animation.setTarget(arrowView)
//        animation.start()

        Log.d("OKAY" , arrowView.right.toString() + " SCREEN WIDTH = " + this.width.toString())

//        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
//                0f,
//                Animation.RELATIVE_TO_SELF,
//                0f)
//        rotate.duration = 5000
//        rotate.interpolator = LinearInterpolator()
//
//        arrowView.startAnimation(rotate)


//        val animator = arrowView.animate()
//        animator.rotation(90f)
//        animator.duration = 5000
//        animator.interpolator = LinearInterpolator()
//        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("MYTAG", "onDraw Called")
        /*val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrix: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrix)*/


        /*canvas?.drawRect(0f, 0f,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, displayMetrix),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, displayMetrix),
                paint)*/


        /*val rectOne = Rect(0, 0, 50, 50)
        rectOne.offset(100, 0)
        rectOne.offsetTo(400, 44)
        canvas?.drawRect(rectOne, paint)*/

        /* val rectFOne = RectF(0f, 0f, 500f, 500f)
         rectFOne.offset(100f, 100f)
         canvas?.drawArc(rectFOne, 0f, -360f, true, paint)*/


        //-=-------------------

        val width = canvas?.width ?: 0
        val height = canvas?.height ?: 0


        val circleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val circleCanvas = Canvas(circleBitmap)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.style = Paint.Style.FILL_AND_STROKE
        p.color = Color.BLUE
        circleCanvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2).toFloat(), p)


        p.color = Color.RED
        val squareBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val squareCanvas = Canvas(squareBitmap)
        val squareRect = Rect(0, 0, width, height)
        squareCanvas.drawRect(squareRect, p)


        /*val q = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.drawBitmap(squareBitmap, 0f, 0f, q)
        q.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas?.drawBitmap(circleBitmap, 0f, 0f, q)


        q.xfermode = null
        setLayerType(LAYER_TYPE_HARDWARE, q)*/


        p.style = Paint.Style.STROKE
        p.strokeWidth = 130f
        p.color = resources.getColor(R.color.cyan)
        /*p.isAntiAlias = false
        val linear = LinearGradient(0f,0f, 100f, 100f, resources.getColor(R.color.black_one),
                resources.getColor(R.color.black_two), Shader.TileMode.MIRROR)
        p.setShader(linear)

        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 3 - 10).toFloat(), p)
        p.setShader(null)*/
        p.strokeWidth = 130f
        p.isAntiAlias = true
        p.setShadowLayer(8f, 0f,0f, R.color.black_two)
        setLayerType(View.LAYER_TYPE_SOFTWARE, p)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 3).toFloat(), p)

    }


    class ArrowView : View {

        constructor(context: Context) : super(context) {

        }

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        }

        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val path = Path()
            path.moveTo(40f, (height / 2 - 20).toFloat())
            path.lineTo((width / 2).toFloat(), (height / 2 - 90).toFloat())
            path.lineTo((width / 2).toFloat(), (height / 2 + 90).toFloat())
            path.lineTo(40f, (height / 2 + 20).toFloat())
            path.close()


            path.addArc((width / 2 - 90).toFloat(), (height / 2 - 90).toFloat(),
                    (width / 2 + 90).toFloat(), (height / 2 + 90).toFloat(), 90f, -180f)

            path.addArc(20f, (height / 2 - 20).toFloat(),
                    60f, (height / 2 + 20).toFloat(), 90f, 180f)



            val paint = Paint()
            paint.style = Paint.Style.FILL
            paint.color = Color.WHITE
            paint.isAntiAlias = true
           /* val linear = LinearGradient(0f,0f, 100f, 100f, resources.getColor(R.color.black_one),
                    resources.getColor(R.color.black_two), Shader.TileMode.MIRROR)
            paint.setShader(linear)

            canvas.drawPath(path, paint)

            paint.setShader(null)*/
            paint.setShadowLayer(8f, 0f,0f, R.color.black_two)
            //setLayerType(LAYER_TYPE_SOFTWARE, paint)
            canvas.drawPath(path, paint)

        }

    }


    class PathExample : View {

        val path = Path()
        val paint = Paint()
        var position: Float = 25f
        set(value) {
            field = 25f + value
        }


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
            paint.color = resources.getColor(R.color.cyan_dark)
            paint.strokeCap = Paint.Cap.ROUND
            paint.isAntiAlias = true

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


            //   invalidate()

        }


    }


}