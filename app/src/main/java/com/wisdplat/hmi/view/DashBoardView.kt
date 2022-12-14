package com.wisdplat.hmi.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.wisdplat.hmi.R
import java.text.DecimalFormat
import kotlin.math.min
import kotlin.math.round

/**
 * @author songyinan
 * @email songynes@foxmail.com
 * @creat 2022-08-10
 * */

class DashBoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val DEFAULT_COLOR_MIDDLE: Int = Color.parseColor("#228fbd") //刻度颜色
        private const val DEFAULT_COLOR_TITLE: Int = Color.WHITE //默认文字画笔颜色
        private const val DEFAULT_TEXT_SIZE_DIAL = 11 //默认刻度数字大小
        private const val DEFAULT_STROKE_WIDTH = 2  //默认最外发光圆弧宽度
        private const val DEFAULT_RADIUS_DIAL = 128 //默认半径大小
        private const val DEFAULT_TITLE_SIZE = 22   //默认中间速度文字大小
        private const val DEFAULT_ANIM_PLAY_TIME = 1000 // 默认扫描动画缓冲时间 原来为2000
        private const val DEFAULT_BORDER = 5   // 发光圆弧外边距
        private const val DEFAULT_CLOCK_POINT_NUM = 100   // 默认表盘
    }

    private var colorDialMiddle = 0 //刻度颜色
    private var textSizeDial = 0    //刻度数字
    private var strokeWidthDial = 0  //最外发光圆弧宽度
    private var titleDialSize = 0   //中间文字大小
    private var titleDialColor = 0  //中间文字颜色
    private var animPlayTime = 0    //动画播放时间
    private val openAngle = 120f // 底部开口的角度

    private var radiusDial = 0 //最大圆的半径
    private var mRealRadius = 0
    private var currentValue = 0f
    private var clockPointNum = 100 //圆盘刻度总数
    private var clockMinValue = 0   //疑似圆盘初始值
    private var dataUnit = "km/h"  //中间显示文字

    private var arcPaint: Paint? = null  //圆弧的画笔
    private var mRect: RectF? = null
    private var pointerPaint: Paint? = null
    private var fontMetrics: Paint.FontMetrics? = null //这玩意儿是绘制文本内容时存储该文本内容位置信息的一个类。
    private var titlePaint: Paint? = null
    private var pointerPath: Path? = null

    init {
        //        关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        //        初始化属性
        initAttrs(context, attrs!!)
        initPaint()
    }

    //属性
    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DashBoardView)
        colorDialMiddle = attributes.getColor(
            R.styleable.DashBoardView_color_dial_middle, DEFAULT_COLOR_MIDDLE
        )
        textSizeDial = attributes.getDimension(
            R.styleable.DashBoardView_text_size_dial,
            sp2px(DEFAULT_TEXT_SIZE_DIAL).toFloat()
        ).toInt()
        strokeWidthDial = attributes.getDimension(
            R.styleable.DashBoardView_stroke_width_dial,
            dp2px(DEFAULT_STROKE_WIDTH).toFloat()
        ).toInt()
        radiusDial = attributes.getDimension(
            R.styleable.DashBoardView_radius_circle_dial,
            dp2px(DEFAULT_RADIUS_DIAL).toFloat()
        ).toInt()
        titleDialSize = attributes.getDimension(
            R.styleable.DashBoardView_text_title_size,
            dp2px(DEFAULT_TITLE_SIZE).toFloat()
        ).toInt()
        titleDialColor = attributes.getColor(
            R.styleable.DashBoardView_text_title_color,
            DEFAULT_COLOR_TITLE
        )
        animPlayTime = attributes.getInt(
            R.styleable.DashBoardView_animator_play_time,
            DEFAULT_ANIM_PLAY_TIME
        )
        dataUnit = attributes.getString(
            R.styleable.DashBoardView_text_data_unit
        ).toString()

        clockPointNum =
            attributes.getInt(
                R.styleable.DashBoardView_text_clock_point_num,
                DEFAULT_CLOCK_POINT_NUM
            )
        attributes.recycle()
    }

    //画笔
    private fun initPaint() {
        arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        arcPaint!!.style = Paint.Style.STROKE
        arcPaint!!.strokeWidth = strokeWidthDial.toFloat()
        arcPaint!!.setShadowLayer(10f, 0f, 0f, Color.parseColor("#35FCFB"))

        pointerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        pointerPaint!!.textSize = textSizeDial.toFloat()
        pointerPaint!!.textAlign = Paint.Align.CENTER
        fontMetrics = pointerPaint!!.fontMetrics

        titlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        titlePaint!!.textAlign = Paint.Align.CENTER
        titlePaint!!.isFakeBoldText = true

        pointerPath = Path()
    }


    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var mWidth: Int
        var mHeight: Int
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize
        } else {
            mWidth = paddingLeft + radiusDial * 2 + paddingRight
            if (widthMode == MeasureSpec.AT_MOST) {
                mWidth = min(mWidth, widthSize)
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize
        } else {
            mHeight = paddingTop + radiusDial * 2 + paddingBottom
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = min(mHeight, heightSize)
            }
        }

        setMeasuredDimension(mWidth, mHeight)

        radiusDial = min(
            measuredWidth - paddingLeft - paddingRight,
            measuredHeight - paddingTop - paddingBottom
        ) / 2
        mRealRadius = radiusDial - strokeWidthDial / 2 - DEFAULT_BORDER * 2
        mRect = RectF(
            (-mRealRadius - DEFAULT_BORDER).toFloat(), (-mRealRadius - DEFAULT_BORDER).toFloat(),
            (mRealRadius + DEFAULT_BORDER).toFloat(), (mRealRadius + DEFAULT_BORDER).toFloat()
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //        step1 画圆弧
        drawArc(canvas!!)
        //        step2 绘制刻度和数字
        drawPointerLine(canvas)
        //        step3 画指针阴影
        drawPointShadow(canvas)
        //        step4 绘制中间黑色圆形背景
        drawBlackCircle(canvas)
        //        step5 绘制表针
        drawPointer(canvas)
        //        step6 绘制深蓝色发光圆形
        drawBlueCircle(canvas)
        //        step7 绘制表盘中的数字
        drawCircleText(canvas)
    }

    //绘制发光弧形
    private fun drawArc(canvas: Canvas) {
        canvas.translate(
            (paddingLeft + radiusDial).toFloat(),
            (paddingTop + radiusDial).toFloat()
        ) //画布平移(x,y)

        arcPaint?.apply {
            shader = null //设置着色器
            style = Paint.Style.STROKE //只绘制图形轮廓（描边）
            isAntiAlias = true //是否抗锯齿
            alpha = 70
            strokeWidth = strokeWidthDial.toFloat() //设置笔划粗细
        }
        // 第一个参数为模糊半径，越大越模糊。 第二个参数是阴影离开文字的x横向距离。
        // 第三个参数是阴影离开文字的Y横向距离。 第四个参数是阴影颜色。
        arcPaint?.setShadowLayer(//设置阴影
            10F,
            0F,
            0F,
            Color.parseColor("#FFFFFF")
        )
        arcPaint?.color = Color.parseColor("#38F9FD") //解析颜色字符串，返回int
        /*绘制圆弧
        * 第一个参数：oval为确定圆弧区域的矩形，圆弧的中心点为矩形的中心点
        * 第二个参数：startAngle为圆弧的开始角度（时钟3点的方向为0度，顺时钟方向为正）
        * 第三个参数：sweepAngle为圆弧的扫过角度（正数为顺时钟方向，负数为逆时钟方向）
        * 第四个参数：useCenter表示绘制的圆弧是否与中心点连接成闭合区域
        * 第五个参数：paint为绘制圆弧的画笔*/
        canvas.drawArc(mRect!!, 150F, (360 - openAngle), false, arcPaint!!)
    }

    //绘制刻度和数字
    private fun drawPointerLine(canvas: Canvas) {
        canvas.rotate(150f) //旋转画布
        val clockPointTmpNum: Int
        var numBigFlag = true
        //判断表盘刻度大小
        //如果小于一百，对其做处理使可以正常显示
        if (clockPointNum / 10 < 10) {
            numBigFlag = false
            clockPointTmpNum = clockPointNum * 10
        } else {
            clockPointTmpNum = clockPointNum
        }
        for (i in 0..clockPointTmpNum) {
            pointerPaint?.color = colorDialMiddle //设置刻度颜色
            if (i % 10 == 0) {    //长表针
                pointerPaint?.strokeWidth = 3f //刻度宽度
                // 该方法用于在画布上绘制直线，通过指定直线的两个端点坐标来绘制。
                // 该方法只能绘制单条直线；如果需要同时绘制多条直线，则可以使用drawLines方法。
                canvas.drawLine(    //传参起点坐标,终点坐标,画笔
                    (radiusDial - DEFAULT_BORDER - strokeWidthDial).toFloat(),
                    0f,
                    (radiusDial - strokeWidthDial - dp2px(15)).toFloat(),
                    0f,
                    pointerPaint!!
                )
                drawPointerText(canvas, i, clockPointTmpNum, numBigFlag)
            } else if (i % 5 == 0) {    //短表针
                pointerPaint?.strokeWidth = 2f
                canvas.drawLine(
                    (radiusDial - DEFAULT_BORDER - strokeWidthDial).toFloat(),
                    0f,
                    (radiusDial - strokeWidthDial - dp2px(9)).toFloat(),
                    0f,
                    pointerPaint!!
                )
            }
            canvas.rotate((360 - openAngle) / clockPointTmpNum) //不停旋转画布坐标系绘制刻度
        }
        canvas.rotate(-((180 - openAngle) / 2 + ((360 - openAngle) / clockPointTmpNum))) //暂不明确，疑似画布复位
    }

    //刻度下的数字
    private fun drawPointerText(
        canvas: Canvas,
        i: Int,
        clockPointTmpNum: Int,
        numBigFlag: Boolean
    ) {
        canvas.save()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pointerPaint!!.color = resources.getColor(R.color.white, null)
        } //设置文字颜色
        val currentCenterX =
            (radiusDial - strokeWidthDial - dp2px(21) - pointerPaint!!.measureText(i.toString()) / 2)
        canvas.translate(currentCenterX, 0f)
        //旋转画布去画text
        canvas.rotate(360 - 150 - (360 - openAngle) / clockPointTmpNum * i)
        val textBaseLine =
            (0 + (fontMetrics!!.bottom - fontMetrics!!.top) / 2 - fontMetrics!!.bottom).toInt()
        if (numBigFlag) {
            canvas.drawText(
                (i + clockMinValue).toString(),
                0f,
                textBaseLine.toFloat(),
                pointerPaint!!
            )
        } else {
            canvas.drawText(
                ((i / 10) + clockMinValue).toString(),
                0f,
                textBaseLine.toFloat(),
                pointerPaint!!
            )
        }
        canvas.restore()
    }

    //3.绘制指针阴影
    private fun drawPointShadow(canvas: Canvas) {
        val currentDegree =
            ((currentValue - clockMinValue) * ((360 - openAngle) / clockPointNum) + 150).toInt()
        canvas.rotate(currentDegree.toFloat())
        val colorSweep = intArrayOf(-0x55001614, 0x0028E9EC, -0x55d71614) //疑似扫过的颜色
        val position = floatArrayOf(0f, 0.9f, 1f)
        val mShader = SweepGradient(0f, 0f, colorSweep, position) //围绕中心点绘制扫描渐变的着色器

        arcPaint!!.apply {
            shader = mShader
            style = Paint.Style.STROKE
            strokeWidth = (radiusDial * 0.4).toFloat()
            clearShadowLayer()
        }

        //绘制矩形
        val mRect = RectF(
            (-mRealRadius - DEFAULT_BORDER + radiusDial * 0.2).toFloat(),
            (-mRealRadius - DEFAULT_BORDER + radiusDial * 0.2).toFloat(),
            (mRealRadius + DEFAULT_BORDER - radiusDial * 0.2).toFloat(),
            (mRealRadius + DEFAULT_BORDER - radiusDial * 0.2).toFloat()
        )
        //绘制圆弧
        canvas.drawArc(
            mRect,
            (360 - (currentDegree - 150)).toFloat(),
            (currentDegree - 150).toFloat(),
            false,
            arcPaint!!
        )
    }

    //4.绘制中间黑色圆形背景
    private fun drawBlackCircle(canvas: Canvas) {
        canvas.restore()
        canvas.translate((paddingLeft + radiusDial).toFloat(), (paddingTop + radiusDial).toFloat())
        val pointerPaint = Paint()
        pointerPaint.isAntiAlias = true
        pointerPaint.style = Paint.Style.FILL
        pointerPaint.color = Color.parseColor("#05002D")
        canvas.drawCircle(0f, 0f, (radiusDial * 0.6).toFloat(), pointerPaint)
    }

    //5.绘制表针
    private fun drawPointer(canvas: Canvas) {
        canvas.save()
        val currentDegree =
            ((currentValue - clockMinValue) * ((360 - openAngle) / clockPointNum) + 150).toInt()
        canvas.rotate(currentDegree.toFloat())
        titlePaint!!.color = Color.WHITE
        titlePaint!!.isAntiAlias = true
        pointerPath!!.apply {
            moveTo((radiusDial - dp2px(12)).toFloat(), 0f)
            lineTo(0f, (-dp2px(5)).toFloat())
            lineTo(-12f, 0f)
            lineTo(0f, dp2px(5).toFloat())
            close()
        }

        canvas.drawPath(pointerPath!!, titlePaint!!)
        canvas.save()
        canvas.restore()
    }

    //6.绘制深蓝色发光圆形
    private fun drawBlueCircle(canvas: Canvas) {
        canvas.rotate(0f)
        canvas.restore()
        val pointerPaint = Paint()
        pointerPaint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.parseColor("#050D3D")
            setShadowLayer(15f, 0f, 0f, Color.parseColor("#006EC6"))
        }
        canvas.drawCircle(0f, 0f, (radiusDial * 0.4).toFloat(), pointerPaint)
    }

    //7.绘制表盘文字
    private fun drawCircleText(canvas: Canvas) {
        //titlePaint!!.color = Color.WHITE
        titlePaint!!.color = titleDialColor
        titlePaint!!.textSize = titleDialSize.toFloat()
        canvas.drawText(formatData(currentValue)!!, 0f, 0f, titlePaint!!)
        titlePaint!!.color = Color.parseColor("#38F9FD")
        titlePaint!!.textSize = sp2px(14).toFloat()
        canvas.drawText(dataUnit, 0f, dp2px(18).toFloat(), titlePaint!!)
    }

    //8.动画
    fun setCompleteDegree(degree: Float) {
        val animator = ValueAnimator.ofFloat(currentValue, degree)
        animator.addUpdateListener { animation ->
            currentValue =
                round(animation.animatedValue as Float * 10) / 10
            invalidate()
        }
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = animPlayTime.toLong()
        animator.start()
    }

    //dp转px
    private fun dp2px(dpVal: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    //sp转px
    private fun sp2px(spVal: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    private fun formatData(num: Float): String? {
        val decimalFormat = DecimalFormat("###.#")
        return decimalFormat.format(num)
    }
}