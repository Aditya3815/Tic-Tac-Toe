package com.example.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.example.tictactoe.R
import utils.AppConstants

class GameResultActivity : AppCompatActivity() {
    private var winnerMes: String? = null
    private lateinit var plyAgain: AppCompatButton
    private lateinit var lavAnimation: LottieAnimationView
    private lateinit var tvWinnerMessage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_result)

        setupUI()
        intialiseViews()
    }

    private fun intialiseViews() {
        plyAgain = findViewById(R.id.wyf)
        tvWinnerMessage = findViewById(R.id.tvWinnerMessage)
        lavAnimation = findViewById(R.id.lavAnimation)
    }


    private fun setupUI() {
        winnerMes = intent?.getStringExtra(AppConstants.WINNER_MESSAGE) ?: ""
        tvWinnerMessage.text = winnerMes
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        plyAgain.setOnClickListener {
            startActivity(
                Intent(this@GameResultActivity, MainActivity::class.java).apply {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
    }

}