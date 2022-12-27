package com.qiantao.rtlimage.view

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import com.qiantao.rtlimage.RtlHelper

/**
 * Created by qian.tao on 2022/12/26
 */
class RtlGridLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr) {

    private val rtlHelper = RtlHelper(this, attrs, defStyleAttr)

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
        rtlHelper.setBackgroundResource(resId)
    }
}