package com.kotlin.library

import android.graphics.Bitmap
import android.graphics.PointF
import android.media.MediaMetadataRetriever
import android.os.Environment
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.*
import com.kotlin.baselibrary.BaseActivity
import com.kotlin.library.view.RecordButtonCallBack
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import com.tencent.smtt.sdk.b.a.e
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.ExifInterface
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException


class MainActivity : BaseActivity() {

    private var fragments = mutableListOf<Fragment>()

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView() {
//        fragments.addBy(IndexFragment())
//            .addBy(SecondFragment())
//            .addBy(ThreeFragment())
//            .addBy(FourFragment())
//        mViewPager.adapter = MainPagerAdapte(supportFragmentManager, fragments)
//        alphaIndicator.setViewPager(mViewPager)

        cameraKitView.setLifecycleOwner(this)
        cameraKitView.mode = Mode.VIDEO
//        cameraKitView.audioBitRate
        cameraKitView.playSounds = true //快门，对焦声音
        cameraKitView.flash = Flash.AUTO //闪光灯自动开启
        cameraKitView.mapGesture(Gesture.PINCH, GestureAction.ZOOM) //手势缩放
        cameraKitView.mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS) // 点击获取焦点
//        cameraKitView.setLocation() //视频拍摄位置记录
        cameraKitView.addCameraListener(object : CameraListener() {

            override fun onVideoTaken(result: VideoResult) {
                //视频结果
                setExif(result.file.path)
                var bitmap = getImage(result.file.path)

                ivBg.setImageBitmap(bitmap)
                val scaleIaAimation = ScaleAnimation(
                    1f, (ivVideo.width.toFloat() / ivBg.width.toFloat()),
                    1f, ivVideo.height.toFloat() / ivBg.height.toFloat()
                )

                val animation = TranslateAnimation(
                    Animation.ABSOLUTE, 0f,
                    Animation.ABSOLUTE, ivVideo.x,
                    Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, ivVideo.y
                )
                var animSet = AnimationSet(false)
                animSet.fillAfter = true
                animSet.duration = 300
                animSet.addAnimation(scaleIaAimation)
                animSet.addAnimation(animation)
                ivBg.startAnimation(animSet)
                animSet.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        ivVideo.setImageBitmap(bitmap)
                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }

                })
            }


            //摄像头旋转要执行的操作，逆时针
            override fun onOrientationChanged(orientation: Int) {
                ToastUtils.showShort(orientation.toString())
            }

            //自动对焦开始
            override fun onAutoFocusStart(point: PointF) {
                super.onAutoFocusStart(point)
            }

            //自动对焦结束
            override fun onAutoFocusEnd(successful: Boolean, point: PointF) {
                super.onAutoFocusEnd(successful, point)
            }
        })

        btnVideo.callBack = object : RecordButtonCallBack {
            override fun StartRecord() {
                if (cameraKitView.isTakingVideo) {
                    return
                }
                var path = getTempFilePath()
                cameraKitView.takeVideo(File(path))
            }

            override fun RecordFinsh() {
                cameraKitView.stopVideo()
            }

        }

    }

    /**
     * 獲取視頻圖片
     */
    fun getImage(mPath: String): Bitmap {
        var media = MediaMetadataRetriever()
        media.setDataSource(mPath)
        //frameTime的单位为us微秒
        return media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
    }

    /**
     * 创建文件路径
     */
    private fun getTempFilePath(): String {
        val fileDir = String.format(
            Locale.getDefault(),
            "%s/Record/",
            Environment.getExternalStorageDirectory().absolutePath
        )
        if (!FileUtils.createOrExistsDir(fileDir)) {
            LogUtils.e("文件夹创建失败：%s", fileDir)
        }
        val fileName = String.format(
            Locale.getDefault(), "record_tmp_%s", TimeUtils.getNowString(
                SimpleDateFormat(
                    "yyyyMMddHHmmss",
                    Locale.SIMPLIFIED_CHINESE
                )
            )
        )
        return String.format(Locale.getDefault(), "%s%s.mp4", fileDir, fileName)
    }

    /**
     * 设置exif信息
     */
    fun setExif(filepath: String) {
        var exif: ExifInterface? = null
        try {
            exif = ExifInterface(filepath)     //根据图片的路径获取图片的Exif
        } catch (ex: IOException) {
            LogUtils.e("Mine", "cannot read exif", ex)
        }
        if (exif == null)
            return
        exif.setAttribute(ExifInterface.TAG_DATETIME, TimeUtils.getNowString())              //把时间写进exif
        exif.setAttribute(ExifInterface.TAG_MODEL, android.os.Build.MODEL)             //设备型号
        try {
            exif.saveAttributes()         //最后保存起来
        } catch (e: IOException) {
            Log.e("Mine", "cannot save exif", e)
        }

    }
}




