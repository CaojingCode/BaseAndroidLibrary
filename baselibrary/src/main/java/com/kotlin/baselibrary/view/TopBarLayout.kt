package com.kotlin.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.kotlin.baselibrary.R
import kotlinx.android.synthetic.main.topbar_laout.view.*


/**
 * Created by Caojing on 2019/8/28.
 *  你不是一个人在战斗
 */
class TopBarLayout(context: Context?) : ConstraintLayout(context) {

    //标题文本
    var tittleText: String = "标题"
    //标题字体大小
    var tittleSize: Float = 0f
    //标题文字颜色
    var tittleColor: Int = 0
    //返回键图片颜色
    var imgBackColor: Int = 0


    constructor(context: Context, attrs: AttributeSet) : this(context) {
        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.topbar_laout, this, true)

        //自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar)
        tittleText = typedArray.getString(R.styleable.TopBar_titleText).toString()
        tittleSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 25f)
        tittleColor = typedArray.getColor(
            R.styleable.TopBar_titleTextColor,
            context.resources.getColor(R.color.qmui_config_color_pure_black)
        )
        imgBackColor = typedArray.getColor(
            R.styleable.TopBar_imgBackColor,
            resources.getColor(R.color.qmui_config_color_pure_black)
        )

        typedArray.recycle()
    }

    /**
     * 控件加载完成后调用此方法
     */
    override fun onFinishInflate() {
        super.onFinishInflate()

        if (tittleText.isNotEmpty()) {
            setBarTittleText(tittleText)
        } else {
            setBarTittleText("")
        }
        setBarTittleColor(tittleColor)
        setBarTittleSize(tittleSize)
        setBarImgBackColor(imgBackColor)
    }

    //设置标题文本
    fun setBarTittleText(text: String) {
        this.tvTittle.text = text
    }

    //设置标题颜色
    fun setBarTittleColor(tittleColor: Int) {
        this.tvTittle.setTextColor(tittleColor)
    }

    //设置标题颜色
    fun setBarTittleSize(tittleSize: Float) {
        this.tvTittle.textSize = tittleSize
    }

    //设置返回键箭头颜色
    fun setBarImgBackColor(imgBackColor: Int) {
        this.ivBack.setColorFilter(imgBackColor)
    }
}