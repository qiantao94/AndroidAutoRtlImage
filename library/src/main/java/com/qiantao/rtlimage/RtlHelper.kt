package com.qiantao.rtlimage

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat

/**
 * Created by qian.tao on 2022/12/26
 */
class RtlHelper(private val view: View, attrs: AttributeSet?, defStyleAttr: Int) {

    private val invalidId = 0
    private var isMirrorSrc = false
    private var isMirrorBackground = false
    private var backgroundResId = invalidId
    private var srcResId = invalidId

    init {
        val a = view.context.obtainStyledAttributes(attrs, R.styleable.RtlHelper, defStyleAttr, 0)
        isMirrorSrc = a.getBoolean(R.styleable.RtlHelper_mirrorSrc, false)
        isMirrorBackground = a.getBoolean(R.styleable.RtlHelper_mirrorBackground, false)
        backgroundResId = a.getResourceId(R.styleable.RtlHelper_android_background, invalidId)
        srcResId = a.getResourceId(R.styleable.RtlHelper_android_src, invalidId)
        a.recycle()
        apply()
    }

    fun setBackgroundResource(resId: Int) {
        backgroundResId = resId
        apply()
    }

    fun setImageResource(resId: Int) {
        srcResId = resId
        apply()
    }

    private fun apply() {
        if (view is ImageView && isMirrorSrc && srcResId != invalidId) {
            val drawable = AppCompatResources.getDrawable(view.context, srcResId)
            drawable?.isAutoMirrored = true
            view.setImageDrawable(drawable)
        }
        if (isMirrorBackground && backgroundResId != invalidId) {
            val drawable = AppCompatResources.getDrawable(view.context, backgroundResId)
            drawable?.isAutoMirrored = true
            ViewCompat.setBackground(view, drawable)
        }
    }

    companion object {

        fun hasRtlAttrs(context: Context, attrs: AttributeSet): Boolean {
            val a = context.obtainStyledAttributes(attrs, R.styleable.RtlHelper, 0, 0)
            val hasMirrorSrc = a.hasValue(R.styleable.RtlHelper_mirrorSrc)
            val hasMirrorBackground = a.hasValue(R.styleable.RtlHelper_mirrorBackground)
            a.recycle()
            return hasMirrorSrc || hasMirrorBackground
        }

    }
}