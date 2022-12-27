package com.qiantao.rtlimage

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import com.qiantao.rtlimage.view.*
import java.lang.reflect.Constructor


/**
 * Created by qian.tao on 2022/12/26
 */
class RtlFactory : LayoutInflater.Factory2 {

    private val constructorSignatures = arrayOf(
        Context::class.java, AttributeSet::class.java
    )

    private val classPrefixes = arrayOf(
        "android.widget.",
        "android.view.",
        "android.webkit."
    )

    private val constructorMap = ArrayMap<String, Constructor<out View?>>()

    private val constructorArgs = arrayOfNulls<Any>(2)

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        if (RtlHelper.hasRtlAttrs(context, attrs).not()) {
            if (context is AppCompatActivity) {
                val delegate = context.delegate
                return delegate.createView(parent, name, context, attrs)
            }
        }

        var view = createViewFromInflater(parent, context, name, attrs)

        if (view == null) {
            view = createViewFromTag(context, name, attrs)
        }

        return view
    }

    private fun createViewFromInflater(
        parent: View?,
        context: Context,
        name: String,
        attrs: AttributeSet
    ): View? {
        when (name) {
            "Button" -> {
                return RtlButton(context, attrs)
            }
            "CheckBox" -> {
                return RtlCheckBox(context, attrs)
            }
            "androidx.constraintlayout.widget.ConstraintLayout" -> {
                return RtlConstraintLayout(context, attrs)
            }
            "EditText" -> {
                return RtlEditText(context, attrs)
            }
            "FrameLayout" -> {
                return RtlFrameLayout(context, attrs)
            }
            "GridLayout" -> {
                return RtlGridLayout(context, attrs)
            }
            "ImageButton" -> {
                return RtlImageButton(context, attrs)
            }
            "ImageView" -> {
                return RtlImageView(context, attrs)
            }
            "LinearLayout" -> {
                return RtlLinearLayout(context, attrs)
            }
            "ListView" -> {
                return RtlListView(context, attrs)
            }
            "RadioButton" -> {
                return RtlRadioButton(context, attrs)
            }
            "RadioGroup" -> {
                return RtlRadioGroup(context, attrs)
            }
            "androidx.recyclerview.widget.RecyclerView" -> {
                return RtlRecyclerView(context, attrs)
            }
            "RelativeLayout" -> {
                return RtlRelativeLayout(context, attrs)
            }
            "ScrollView" -> {
                return RtlScrollView(context, attrs)
            }
            "TextView" -> {
                return RtlTextView(context, attrs)
            }
            "View" -> {
                return RtlView(context, attrs)
            }
            else -> {
                if (context is AppCompatActivity) {
                    val delegate = context.delegate
                    return delegate.createView(parent, name, context, attrs)
                }
            }
        }
        return null
    }

    private fun createViewFromTag(context: Context, name: String, attrs: AttributeSet): View? {
        var n = name
        if ("view" == n) {
            n = attrs.getAttributeValue(null, "class")
        }
        try {
            constructorArgs[0] = context
            constructorArgs[1] = attrs
            if (-1 == n.indexOf('.')) {
                for (i in classPrefixes.indices) {
                    val view = createView(context, n, classPrefixes[i])
                    if (view != null) {
                        return view
                    }
                }
                return null
            } else {
                return createView(context, n, null)
            }
        } catch (e: Exception) {
            return null
        } finally {
            constructorArgs[0] = null
            constructorArgs[1] = null
        }
    }

    private fun createView(context: Context, name: String, prefix: String?): View? {
        var constructor: Constructor<out View?>? = constructorMap[name]
        return try {
            if (constructor == null) {
                val clazz = context.classLoader.loadClass(
                    if (prefix != null) prefix + name else name
                ).asSubclass(View::class.java)
                constructor =
                    clazz.getConstructor(*constructorSignatures)
                constructorMap[name] = constructor
            }
            constructor?.isAccessible = true
            constructor?.newInstance(*constructorArgs)
        } catch (e: java.lang.Exception) {
            null
        }
    }

    override fun onCreateView(
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return onCreateView(null, name, context, attrs)
    }

}