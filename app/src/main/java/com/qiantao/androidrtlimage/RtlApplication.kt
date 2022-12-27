package com.qiantao.androidrtlimage

import android.app.Application
import android.content.Context
import com.qiantao.rtlimage.RtlImageLibrary

/**
 * Created by qian.tao on 2022/12/26
 */
class RtlApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LanguageHelper.createContext(base))
    }

    override fun onCreate() {
        super.onCreate()
        RtlImageLibrary.inject(this)
    }
}