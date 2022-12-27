package com.qiantao.rtlimage

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat


/**
 * Created by qian.tao on 2022/12/26
 */
class RtlLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        val inflater = activity.layoutInflater
        if (inflater.factory2 == null) {
            val factory = RtlFactory()
            inflater.factory2 = factory
        } else if (inflater.factory2 !is RtlFactory) {
            forceSetFactory2(inflater)
        }
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    @SuppressLint("DiscouragedPrivateApi")
    private fun forceSetFactory2(inflater: LayoutInflater) {
        val compatClass = LayoutInflaterCompat::class.java
        val inflaterClass = LayoutInflater::class.java
        try {
            val checkedField = compatClass.getDeclaredField("sCheckedField")
            checkedField.isAccessible = true
            checkedField.setBoolean(compatClass, false)
            val mFactory = inflaterClass.getDeclaredField("mFactory")
            mFactory.isAccessible = true
            val mFactory2 = inflaterClass.getDeclaredField("mFactory2")
            mFactory2.isAccessible = true
            val factory = RtlFactory()
            mFactory2.set(inflater, factory)
            mFactory.set(inflater, factory)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }
}