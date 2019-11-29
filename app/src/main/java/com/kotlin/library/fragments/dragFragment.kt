package com.kotlin.library.fragments

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.kotlin.baselibrary.BaseFragment
import com.kotlin.library.R
import kotlinx.android.synthetic.main.drag_fragment.view.*


/**
 *
 * Created by Caojing on 2019/11/261623
 *
 */
class dragFragment : BaseFragment() {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    var priceX = 0f //价格x轴其实坐标
    var houseLayoutHeight = 0  //房源信息布局初始高度
    var topBarHeight=0 //导航栏初始高度

    lateinit var layoutParams: ViewGroup.LayoutParams //房源信息layoutParams

    lateinit var topLayoutParams:ViewGroup.LayoutParams  //顶部导航layoutParams

    override fun isFullScreen(): Boolean {
        return true
    }


    override fun initView(view: View) {
        bottomSheetBehavior = from(view.llContent)
        view.tvPrice.post {
            priceX = view.tvName.x - view.tvPrice.x
            houseLayoutHeight = view.clTopTittle.height
            layoutParams = view.clTopTittle.layoutParams
            topLayoutParams= view.llTopLayout.layoutParams
            topBarHeight=view.llTopLayout.height
        }
        view.llTopLayout.setOnClickListener {

        }

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                if (p1 < 0)
                    return
                view.tvName.scaleX = p1 * 0.5f + 1
                view.tvName.scaleY = p1 * 0.5f + 1
                view.tvName.translationX = p1 * view.tvName.width / 4

                view.tvPrice.translationX = p1 * priceX
                view.tvPrice.translationY = p1 * view.tvName.height * 1.8f

                view.tvArea.translationY = p1 * view.tvName.height * 1.8f

                layoutParams.height =
                    (houseLayoutHeight + p1 * view.tvName.height * 1.8f + p1 * view.tvName.height * 2f).toInt()
                view.clTopTittle.layoutParams = layoutParams

                topLayoutParams.height= (p1*1.5*view.tvName.height+topBarHeight).toInt()
                view.llTopLayout.layoutParams=topLayoutParams

                if (p1>=1){
                    view.buttonOne.visibility=View.VISIBLE
                    view.buttonTwo.visibility=View.VISIBLE

                }else{
                    view.buttonOne.visibility=View.GONE
                    view.buttonTwo.visibility=View.GONE
                }

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_EXPANDED -> {
                        //展开状态
                    }
                    STATE_COLLAPSED -> {
                        //收缩状态
                        setFullScreen(View.GONE)
                    }
                    else -> {

                    }
                }
            }

        })
    }

    override fun layoutId(): Int {
        return R.layout.drag_fragment
    }

    /**
     * 设置底部弹出fragment显示与隐藏
     */
    fun setGoneBottomSheet() {
        if (bottomSheetBehavior.state == STATE_HIDDEN) {
            bottomSheetBehavior.state = STATE_COLLAPSED
        } else {
            bottomSheetBehavior.state = STATE_HIDDEN
        }

    }
}