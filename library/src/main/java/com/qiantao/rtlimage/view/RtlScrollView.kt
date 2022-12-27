package com.qiantao.rtlimage.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import com.qiantao.rtlimage.RtlHelper

/**
 * Created by qian.tao on 2022/12/26
 */
class RtlScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    private val rtlHelper = RtlHelper(this, attrs, 0)

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        rtlHelper.setBackgroundResource(resId)
    }
}