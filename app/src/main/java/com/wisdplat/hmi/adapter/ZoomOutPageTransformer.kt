package com.wisdplat.hmi.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

/**
 *
@author xuleyu
@Email xuleyumail@gmail.com
@create 2022-07-29 15:01
 * ViewPager自定义切换页面动画
 */
private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    //这个页面在左侧屏幕外
                    alpha = 0f
                }

                position <= 1 -> {
                    //修改默认幻灯片过渡以缩小页面
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }
                    //缩小页面（在 MIN_SCALE 和 1 之间）
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    //相对于其大小淡化页面。
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }

                else -> { // (1,+Infinity]
                    //这个页面在右侧屏幕外
                    alpha = 0f
                }
            }
        }
    }
}
