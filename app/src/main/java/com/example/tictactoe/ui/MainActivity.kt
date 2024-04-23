package com.example.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tictactoe.R

class MainActivity : AppCompatActivity() {
    private lateinit var wFY: AppCompatButton
    private lateinit var wA: AppCompatButton
    private lateinit var setting: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContentView(R.layout.activity_main)

        initializeViews()

    }

    private fun initializeViews() {

        wFY = findViewById<AppCompatButton>(R.id.wyf)
        wA = findViewById<AppCompatButton>(R.id.wa)
        setting = findViewById<ImageView>(R.id.setting)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        wFY.setOnClickListener {
            val intent = Intent(this@MainActivity, PlayerDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}