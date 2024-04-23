package com.example.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.R
import com.google.android.material.textfield.TextInputEditText
import utils.AppConstants
import utils.Utility

class PlayerDetailsActivity : AppCompatActivity() {
    private lateinit var ivback: ImageButton
    private lateinit var enterYourName: TextInputEditText
    private lateinit var nxtBtn: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player_details)
        initialiseViews()
    }

    private fun initialiseViews() {
        ivback = findViewById(R.id.ivbackbtn)
        enterYourName = findViewById(R.id.enterYourName)
        nxtBtn = findViewById(R.id.nextBtn)
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        ivback.setOnClickListener {
            finish()
        }
        nxtBtn.setOnClickListener {
            redirectToChooseYourSideActivity()
        }
    }

    private fun redirectToChooseYourSideActivity() {
        val username = enterYourName.text.toString()
        if (username.isEmpty()) {
            enterYourName.error = getString(R.string.please_enter_valid_name)

        } else {
            Utility.hideKeyBoard(this@PlayerDetailsActivity)
            startActivity(Intent(
                this@PlayerDetailsActivity, FriendsDetailsActivity
                ::class.java
            ).apply {
                putExtra(AppConstants.USERNAME, username)
            })
        }

    }
}