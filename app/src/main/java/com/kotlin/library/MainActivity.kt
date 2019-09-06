package com.kotlin.library

import androidx.fragment.app.Fragment
import com.kotlin.baselibrary.BaseActivity
import com.kotlin.baselibrary.addBy
import com.kotlin.library.adapters.MainPagerAdapte
import com.kotlin.library.fragments.IndexFragment
import com.kotlin.library.fragments.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun closeAllWebView() {
    }

    private var fragments = mutableListOf<Fragment>()

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView() {
        fragments.addBy(IndexFragment()).addBy(SecondFragment()).addBy(IndexFragment())
            .addBy(SecondFragment())
        mViewPager.adapter = MainPagerAdapte(supportFragmentManager, fragments)
        alphaIndicator.setViewPager(mViewPager)

    }

}




