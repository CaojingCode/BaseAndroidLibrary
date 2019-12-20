package com.kotlin.library.fragments

import android.Manifest
import android.graphics.Matrix
import android.os.Build
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.baselibrary.BaseFragment
import com.kotlin.library.R
import kotlinx.android.synthetic.main.three_fragment.view.*

/**
 *
 * Created by Caojing on 2019/11/252028
 *
 */
class ThreeFragment : BaseFragment() {

    lateinit var viewFinder: TextureView

    override fun initView(view: View) {
        viewFinder = view.viewFinder
        if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
            PermissionUtils.permission(PermissionConstants.CAMERA)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        viewFinder.post { startCamera() }
                    }

                    override fun onDenied() {
                        //拒绝权限
                        ToastUtils.showShort("拒绝权限，功能无法使用")
                    }
                }).rationale { shouldRequest -> shouldRequest.again(true) }.request()
        } else {
            viewFinder.post { startCamera() }
        }

        viewFinder.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            updateTransform()
        }

    }

    private fun startCamera() {
        //相机配置
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(
                Size(
                    ScreenUtils.getScreenWidth(),
                    ScreenUtils.getScreenHeight()
                )
            )
        }.build()

        // 构建预览对象
        val preview = Preview(previewConfig)

        // Every time the viewfinder is updated, recompute layout
        preview.setOnPreviewOutputUpdateListener {

            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        // Bind use cases to lifecycle
        CameraX.bindToLifecycle(this, preview)
    }

    private fun updateTransform() {

        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees =
            when (viewFinder.display.rotation) {
                Surface.ROTATION_0 -> 0
                Surface.ROTATION_90 -> 90
                Surface.ROTATION_180 -> 180
                Surface.ROTATION_270 -> 270
                else -> return
            }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        viewFinder.setTransform(matrix)

    }

    override fun layoutId(): Int {
        return R.layout.three_fragment
    }
}