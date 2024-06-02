package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     val SPLASH_TIME_OUT: Long = 3000
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        android.os.Handler().postDelayed({
            startActivity(Intent(this, Homepage::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}