package com.qiantao.rtlimage

import android.app.Application

/**
 * Created by qian.tao on 2022/12/26
 */
object RtlImageLibrary {

    @JvmStatic
    fun inject(application: Application) {
        application.registerActivityLifecycleCallbacks(RtlLifecycleCallbacks())
    }

}