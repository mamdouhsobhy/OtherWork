package com.example.otherwork.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.otherwork.R
import com.example.otherwork.auth.ActivityLogin
import com.example.otherwork.extention.setStatusBarColor


class ActivitySplash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBarColor(R.color.color_background, R.color.black)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()

        }, SPLASH_TIME_OUT)
    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 3000
    }

}