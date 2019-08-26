package com.kotlin.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by Caojing on 2019/8/26.
 *  你不是一个人在战斗
 */
class CustomViewPager(context: Context) : ViewPager(context) {

    private var isCanScroll = true


    fun setCanScroll(isCanScroll: Boolean) {
        this.isCanScroll = isCanScroll
    }


    constructor(context: Context, attrs: AttributeSet) : this(context)


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isCanScroll && super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isCanScroll && super.onTouchEvent(ev)
    }
}