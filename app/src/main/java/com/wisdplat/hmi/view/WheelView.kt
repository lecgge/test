package com.wisdplat.hmi.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView


/**
 *
@author zoushunhua
@Email zouhs@foxmail.com
@create 2022-07-29 16:53
 *
 */
class WheelView(context: Context, attrs: AttributeSet?) : ScrollView(context, attrs) {

    companion object {
        const val OFF_SET_DEFAULT = 1  //上下显示字数
        private const val SCROLL_DIRECTION_UP = 0
        private const val SCROLL_DIRECTION_DOWN = 1
    }


    open class OnWheelViewListener {
        open fun onSelected(index: Int, item: String?) {
            //Timber.d("Index $index, ite, $item")
        }
    }

    //    private ScrollView scrollView;
    private var views: LinearLayout? = null

    private var items = ArrayList<String>()


    fun setItems(list: List<String>?) {
        items.clear()
        items.addAll(list!!)

        // 前面和后面补全
        for (i in 0 until offset) {
            items.add(0, "")
            items.add("")
        }
        initData()
    }

    private var scrollerTask: Runnable? = null
    private var offset = OFF_SET_DEFAULT // 偏移量（需要在最前面和最后面补全）
    private var displayItemCount = 0
    private var currentIndex = 4
    private var initialY = 0
    private var newCheck = 50
    private var itemHeight = 0
    private var selectedAreaBorder: IntArray? = null
    private var scrollDirection = -1
    private var paint: Paint? = null
    private var viewWidth = 0
    var onWheelViewListener: OnWheelViewListener? = null


    init {
        this.isVerticalScrollBarEnabled = false
        views = LinearLayout(context)
        views!!.orientation = LinearLayout.VERTICAL
        this.addView(views)
        scrollerTask = Runnable {
            val newY = scrollY
            if (initialY - newY == 0) { // stopped
                val remainder = initialY % itemHeight
                val divided = initialY / itemHeight
                if (remainder == 0) {
                    currentIndex = divided + offset
                    onSelectedCallBack()
                } else {
                    if (remainder > itemHeight / 2) {
                        post {
                            smoothScrollTo(0, initialY - remainder + itemHeight)
                            currentIndex = divided + offset + 1
                            onSelectedCallBack()
                        }
                    } else {
                        post {
                            smoothScrollTo(0, initialY - remainder)
                            currentIndex = divided + offset
                            onSelectedCallBack()
                        }
                    }
                }
            } else {
                initialY = scrollY
                postDelayed(scrollerTask, newCheck.toLong())
            }
        }
    }


    private fun startScrollerTask() {
        initialY = scrollY
        postDelayed(scrollerTask, newCheck.toLong())
    }

    private fun initData() {
        displayItemCount = offset * 2 + 1
        for (item in items) {
            views!!.addView(createView(item))
        }
        refreshItemView()
    }


    private fun createView(item: String): TextView {
        val tv = TextView(context)
        tv.layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tv.isSingleLine = true
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
        tv.text = item
        tv.gravity = Gravity.CENTER
        val padding = dip2px(15f)
        tv.setPadding(padding, padding, padding, padding)
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv)
            //Timber.d("itemHeight: $itemHeight")
            views!!.layoutParams =
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount)
            val lp = this.layoutParams as LinearLayout.LayoutParams
            this.layoutParams = LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount)
        }
        return tv
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        refreshItemView()
        scrollDirection = if (t > oldt) {
            //            Log.d(TAG, "向下滚动");
            SCROLL_DIRECTION_DOWN
        } else {
            //            Log.d(TAG, "向上滚动");
            SCROLL_DIRECTION_UP
        }
    }

    private fun refreshItemView() {


        val childSize = views!!.childCount
        for (i in 0 until childSize) {
            val itemView = views!!.getChildAt(i) as TextView
//            if (position == i) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            itemView.setTextColor(Color.parseColor("#FFFFFF"))
        }
//            } else {
//                itemView.setTextColor(Color.parseColor("#ffffff"))
//            }
//        }
    }

    /**
     * 获取选中区域的边界
     */

    private fun obtainSelectedAreaBorder(): IntArray {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = IntArray(2)
            selectedAreaBorder!![0] = itemHeight * offset
            selectedAreaBorder!![1] = itemHeight * (offset + 1)
        }
        return selectedAreaBorder!!
    }


    override fun setBackgroundDrawable(background1: Drawable?) {

        if (viewWidth == 0) {
            viewWidth = context.resources.displayMetrics.widthPixels


        }
        if (null == paint) {
            paint = Paint()
            paint!!.color = Color.parseColor("#0B2049")
            paint!!.strokeWidth = dip2px(1f).toFloat()
        }
        object : Drawable() {


            override fun draw(canvas: Canvas) {
                canvas.drawLine(
                    (viewWidth / 6).toFloat(),
                    obtainSelectedAreaBorder()[1].toFloat(), (viewWidth * 5 / 6).toFloat(),
                    obtainSelectedAreaBorder()[1].toFloat(),
                    paint!!

                )
                canvas.drawLine(
                    (viewWidth / 6).toFloat(),
                    obtainSelectedAreaBorder()[1].toFloat(), (viewWidth * 5 / 6).toFloat(),
                    obtainSelectedAreaBorder()[1].toFloat(),
                    paint!!
                )
            }

            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(cf: ColorFilter?) {}
            override fun getOpacity(): Int {
                return PixelFormat.TRANSLUCENT
            }
        }
        super.getBackground()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //Timber.d("w: $w, h: $h, oldw: $oldw, oldh: $oldh")
        viewWidth = w
        background = null
    }

    /**
     * 选中回调
     */
    private fun onSelectedCallBack() {
        if (null != onWheelViewListener) {
            onWheelViewListener!!.onSelected(currentIndex, items[currentIndex])
        }
    }

    fun setSelection(position: Int) {
        currentIndex = position + offset
        post {
            smoothScrollTo(0, position * itemHeight)
        }
    }


    override fun fling(velocityY: Int) {
        super.fling(velocityY / 3)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            startScrollerTask()
        }
        return super.onTouchEvent(ev)
    }


    private fun dip2px(dpValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun getViewMeasuredHeight(view: View): Int {
        val width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        view.measure(width, expandSpec)
        return view.measuredHeight
    }


}
