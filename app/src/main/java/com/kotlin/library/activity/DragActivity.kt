package com.kotlin.library.activity

import com.blankj.utilcode.util.ToastUtils
import com.kotlin.baselibrary.BaseActivity
import com.kotlin.library.R
import com.kotlin.library.fragments.dragFragment
import kotlinx.android.synthetic.main.drag_fragment.*
import kotlinx.android.synthetic.main.dragz_layout.*

/**
 *
 * Created by Caojing on 2019/11/261620
 *
 */
class DragActivity : BaseActivity() {

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView() {

        viewClick.setOnClickListener {
            ToastUtils.showShort("点击了里面的布局")
            var dragFragment=supportFragmentManager.findFragmentById(R.id.dragFragment) as dragFragment
            dragFragment.setGoneBottomSheet()
        }

    }

    override fun layoutId(): Int {
        return R.layout.dragz_layout
    }
}