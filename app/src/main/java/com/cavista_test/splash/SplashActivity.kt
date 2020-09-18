package com.cavista_test.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cavista_test.R
import com.cavista_test.main.MainActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME_OUT: Long = 500 // 1/2 second
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadingSplashScreen()
    }

    private fun loadingSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}