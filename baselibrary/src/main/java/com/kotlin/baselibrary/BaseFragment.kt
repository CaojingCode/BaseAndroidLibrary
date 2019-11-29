package com.kotlin.baselibrary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ActivityUtils
import com.qmuiteam.qmui.arch.QMUIFragment
import kotlinx.android.synthetic.main.activity_base.view.*

/**
 * Created by Caojing on 2019/9/5.
 *  你不是一个人在战斗
 */
abstract class BaseFragment : QMUIFragment() {


    lateinit var fragmentView: View

    override fun translucentFull(): Boolean {
        return true
    }

    override fun onCreateView(): View {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.activity_base, null)
        setBackGone()
        setFullScreen()
        initView(setContentLayout(layoutId()))
        return fragmentView
    }

    private fun setContentLayout(layoutId: Int): View {
        if (layoutId <= 0)
            throw SecurityException("请传入布局资源id")
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val contentView = inflater.inflate(layoutId, null)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        fragmentView.llContent.addView(contentView, params)
        return contentView
    }

    abstract fun initView(view: View)

    abstract fun layoutId(): Int

    //设置铺满全屏，隐藏标题栏
    private fun setFullScreen() {
        if (isFullScreen())
            fragmentView.clTopBar.visibility = View.GONE
    }


    fun setFullScreen(visibility:Int) {
            fragmentView.clTopBar.visibility = visibility
    }

    //子类继承父类可实现此方法来设置是否全屏显示
    open fun isFullScreen(): Boolean {
        return false
    }

    //设置标题名称
    fun setTittleText(tittleText: String): BaseFragment {
        fragmentView.tvTittle.text = tittleText
        return this
    }

    //设置标题颜色
    fun setTittleTextColor(colorId: Int): BaseFragment {
        fragmentView.tvTittle.setTextColor(resources.getColor(colorId))
        return this
    }


    //设置标题栏颜色
    fun setTittleBarColor(colorId: Int): BaseFragment {
        fragmentView.clTopBar.setBackgroundColor(resources.getColor(colorId))
        return this
    }

    //fragment中隐藏左边返回按钮
    private fun setBackGone() {
        fragmentView.ivBack.visibility = View.GONE
        fragmentView.tvBack.visibility = View.GONE
    }

}

fun BaseFragment.toIntent(intent: Intent) {
    this.startActivity(intent)
}

fun BaseFragment.toActivity(activity: BaseActivity) {
    ActivityUtils.startActivity(activity::class.java)
}
