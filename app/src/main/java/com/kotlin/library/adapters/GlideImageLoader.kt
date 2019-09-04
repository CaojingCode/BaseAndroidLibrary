package com.kotlin.library.adapters

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kotlin.library.enity.BannerInfo
import com.youth.banner.loader.ImageLoader

/**
 * Created by Caojing on 2019/8/30.
 *  你不是一个人在战斗
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (context != null) {
            if (imageView != null) {
                if (path is BannerInfo.Data){
                    Glide.with(context).load(path.imagePath).into(imageView)
                }else{
                    Glide.with(context).load(path).into(imageView)
                }
            }
        }
    }
}