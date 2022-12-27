package com.qiantao.androidrtlimage

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Created by qian.tao on 2022/12/26
 */
object LanguageHelper {

    private const val LANG_EN = "en"
    private const val LANG_AR = "ar"

    private const val SP_NAME = "language"
    private const val SP_LANGUAGE = "language"

    fun switchLanguage(context: Context): String {
        val curLanguage = getLanguage(context)
        return if (curLanguage == LANG_EN) {
            changeLanguage(context, LANG_AR)
        } else {
            changeLanguage(context, LANG_EN)
        }
    }

    fun changeLanguage(context: Context, language: String): String {
        val preferences = context.getSharedPreferences(SP_NAME, AppCompatActivity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(SP_LANGUAGE, language)
        editor.apply()
        return language
    }

    fun createContext(context: Context?): Context? {
        val locale = createLocale(context)

        var ctx = context

        val res = ctx?.resources
        val config = Configuration(res?.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            config.setLocales(localeList)
        } else {
            config.setLocale(locale)
        }

        ctx = ctx?.createConfigurationContext(config)
        return ctx
    }

    private fun createLocale(context: Context?): Locale {
        val language = getLanguage(context)
        return Locale(language)
    }

    fun getLanguage(context: Context?): String {
        val preferences = context?.getSharedPreferences(SP_NAME, AppCompatActivity.MODE_PRIVATE)
        return preferences?.getString(SP_LANGUAGE, LANG_EN) ?: LANG_EN
    }
}