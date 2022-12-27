package com.qiantao.rtlimage.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.qiantao.rtlimage.RtlHelper

/**
 * Created by qian.tao on 2022/12/26
 */
class RtlImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val rtlHelper = RtlHelper(this, attrs, defStyleAttr)

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        rtlHelper.setImageResource(resId)
    }

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        rtlHelper.setBackgroundResource(resId)
    }
}