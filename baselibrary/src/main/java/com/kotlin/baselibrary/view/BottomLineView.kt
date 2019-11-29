package com.kotlin.baselibrary.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.kotlin.baselibrary.R

/**
 * 底部滑动横线
 * Created by Caojing on 2019/9/27.
 *  你不是一个人在战斗
 */
class BottomLineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintGray = Paint()

    var paintGreen = Paint()

    var startX: Float = 0f
    var endX: Float = 0f  //绿线的长度

    init {
        paintGray.color =
            context?.resources?.getColor(R.color.qmui_config_color_gray_1) ?: Color.GRAY
        paintGreen.color = Color.GREEN
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 5f, 5f, paintGray)
            canvas?.drawRoundRect(
                startX,
                0f,
                endX + startX,
                height.toFloat(),
                5f,
                5f,
                paintGreen
            )
        } else {
            canvas?.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paintGray)
            canvas?.drawLine(startX, 0f, endX + startX, height.toFloat(), paintGreen)
        }
    }

    //设置移动的距离
    fun setMoveDistance(startX: Float, endX: Float) {
        this.startX = startX
        this.endX = endX
        requestLayout()
    }

    //获取绿线的长度
    fun getLineWidth(): Float {
        return endX
    }
}