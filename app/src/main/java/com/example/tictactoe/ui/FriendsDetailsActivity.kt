package com.example.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.GameActivity1
import com.example.tictactoe.R
import com.google.android.material.textfield.TextInputEditText
import utils.AppConstants
import utils.Utility

class FriendsDetailsActivity : AppCompatActivity() {

    private lateinit var ivFriendBackBtn: ImageButton
    private lateinit var enterYourFriendName: TextInputEditText
    private lateinit var nextFriendBtn: AppCompatButton


    private var username:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_friends_details)

        initialiseViews()


    }

    private fun initialiseViews() {
        ivFriendBackBtn = findViewById(R.id.ivFriendBackBtn)
        enterYourFriendName = findViewById(R.id.enterYourFriendName)
        nextFriendBtn = findViewById(R.id.nextFriendBtn)


        username = intent?.getStringExtra(AppConstants.USERNAME)
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        ivFriendBackBtn.setOnClickListener {
            finish()
        }
        nextFriendBtn.setOnClickListener {
            redirectToChooseYourSideActivity()
        }


    }

    private fun redirectToChooseYourSideActivity() {
        val friendname = enterYourFriendName.text.toString()
        if (friendname.isEmpty()) {
            enterYourFriendName.error = getString(R.string.please_enter_valid_name)

        } else {
            Utility.hideKeyBoard(this@FriendsDetailsActivity)
            startActivity(Intent(
                this@FriendsDetailsActivity, ChooseYourSideActivity
                ::class.java
            ).apply {
                putExtra(AppConstants.USERNAME, username)
                putExtra(AppConstants.FRiEND_NAME, friendname)
            })
        }

    }
}