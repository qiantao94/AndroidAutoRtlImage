package com.qiantao.androidrtlimage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSwitch = findViewById<TextView>(R.id.btn_switch)
        btnSwitch.setOnClickListener {
            LanguageHelper.switchLanguage(this)
            restart(this)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LanguageHelper.createContext(base))
    }

    companion object {
        fun restart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}