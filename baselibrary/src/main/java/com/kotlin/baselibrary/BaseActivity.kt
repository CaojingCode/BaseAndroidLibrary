package com.kotlin.baselibrary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ActivityUtils
import com.qmuiteam.qmui.arch.QMUIActivity
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by Caojing on 2019/9/4.
 *  你不是一个人在战斗
 */
abstract class BaseActivity : QMUIActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBack, R.id.tvBack -> backAction()
            R.id.rightButton->closeAllWebView()
        }
    }

    abstract fun closeAllWebView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setContentLayout(layoutId())
        setFullScreen()
        initView()
        ivBack.setOnClickListener(this)
        tvBack.setOnClickListener(this)
        rightButton.setOnClickListener(this)
    }


    private fun setContentLayout(layoutId: Int) {
        if (layoutId <= 0)
            throw SecurityException("请传入布局资源id")
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val contentView = inflater.inflate(layoutId, null)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        llContent.addView(contentView, params)
    }

    abstract fun initView()

    abstract fun layoutId(): Int

    override fun translucentFull(): Boolean {
        return true
    }

    //设置铺满全屏，隐藏标题栏
    private fun setFullScreen() {
        if (isFullScreen())
            clTopBar.visibility = View.GONE
    }

    //子类继承父类可实现此方法来设置是否全屏显示
    open fun isFullScreen(): Boolean {
        return false
    }

    //设置标题名称
    fun setTittleText(tittleText: String): BaseActivity {
        tvTittle.text = tittleText
        return this
    }

    //设置标题颜色
    fun setTittleTextColor(colorId: Int): BaseActivity {
        tvTittle.setTextColor(resources.getColor(colorId))
        return this
    }

    //设置标题栏颜色
    fun setTittleBarColor(colorId: Int): BaseActivity {
        clTopBar.setBackgroundColor(resources.getColor(colorId))
        return this
    }

    //设置返回箭头按钮颜色
    fun setImgBackColor(colorId: Int): BaseActivity {
        ivBack.setColorFilter(resources.getColor(colorId))
        return this
    }

    //设置返回按钮图片资源id
    fun setImgBackRes(resId: Int): BaseActivity {
        ivBack.setImageResource(resId)
        return this
    }

    //设置返回按钮文字
    fun setTextBackText(text: String): BaseActivity {
        tvBack.text = text
        tvBack.visibility = View.VISIBLE
        ivBack.visibility = View.GONE
        return this
    }

    //设置返回按钮文字颜色
    fun setTextBackTextColor(colorId: Int): BaseActivity {
        tvBack.setTextColor(resources.getColor(colorId))
        tvBack.visibility = View.VISIBLE
        ivBack.visibility = View.GONE
        return this
    }

    //点击返回要执行的方法
    open fun backAction() {
        finish()
    }

    override fun doOnBackPressed() {
        backAction()
    }

    private var disableAllClick: Boolean = false

    //禁用所有触摸事件
    fun setDisableAllClick(disableAllClick: Boolean): BaseActivity {
        this.disableAllClick = disableAllClick
        return this
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (!disableAllClick) {
            super.dispatchTouchEvent(ev)
        } else {
            disableAllClick
        }

    }

}

fun BaseActivity.toIntent(intent: Intent) {
    this.startActivity(intent)
}

fun BaseActivity.toActivity(activity: BaseActivity) {
    ActivityUtils.startActivity(activity::class.java)
}


//fun BaseActivity.toActivity(clz: Class<out BaseActivity>) {
//    ActivityUtils.startActivity(clz)
//}