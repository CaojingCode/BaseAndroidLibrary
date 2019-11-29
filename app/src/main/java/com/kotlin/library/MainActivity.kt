package com.kotlin.library

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.baselibrary.BaseActivity
import com.kotlin.baselibrary.addBy
import com.kotlin.library.adapters.MainPagerAdapte
import com.kotlin.library.fragments.IndexFragment
import com.kotlin.library.fragments.SecondFragment
import com.kotlin.library.fragments.ThreeFragment
import com.kotlin.library.http.ApiRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private var fragments = mutableListOf<Fragment>()


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView() {
        fragments.addBy(IndexFragment())
            .addBy(SecondFragment())
            .addBy(IndexFragment())
            .addBy(ThreeFragment())
        mViewPager.adapter = MainPagerAdapte(supportFragmentManager, fragments)
        alphaIndicator.setViewPager(mViewPager)

    }


}




