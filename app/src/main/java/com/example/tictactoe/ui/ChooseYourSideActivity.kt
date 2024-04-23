package com.example.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.GameActivity1
import com.example.tictactoe.R
import utils.AppConstants

class ChooseYourSideActivity : AppCompatActivity() {
    private lateinit var rbcircle: RadioButton
    private lateinit var rbcross: RadioButton
    private lateinit var ivcircle: ImageView
    private lateinit var ivcross: ImageView
    private lateinit var CysNxtBtn: AppCompatButton
    private lateinit var txtshowchoose: TextView
    private lateinit var CysBackBtn: ImageButton
    private lateinit var llSidesContainer: LinearLayout
    private lateinit var rgSidesContainer: RadioGroup
    private lateinit var tvGreetUse: TextView


    var yourSide: String? = null
    var friendName: String? = null
    var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_your_side)
        username = intent?.getStringExtra(AppConstants.USERNAME) ?: ""
        friendName = intent?.getStringExtra(AppConstants.FRiEND_NAME) ?: ""
        initializeView()

    }

    private fun initializeView() {
        rbcircle = findViewById(R.id.rbcircle)
        rbcross = findViewById(R.id.rbcross)
        ivcircle = findViewById(R.id.ivcircle)
        ivcross = findViewById(R.id.ivcross)
        CysNxtBtn = findViewById(R.id.CysNxtBtn)
        txtshowchoose = findViewById(R.id.chooseTextView)
        CysBackBtn = findViewById(R.id.ivCysBackBtn)
        llSidesContainer = findViewById(R.id.llSidesContainer)
        rgSidesContainer = findViewById(R.id.rgSidesContainer)
        tvGreetUse = findViewById(R.id.greetUser)

        username?.also {
            tvGreetUse.text = "Hello $it :)"
        }
        setonClickListeners()
    }

    private fun setonClickListeners() {
        rgSidesContainer.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.rbcircle) {
                sideSelected(AppConstants.TEAM_CIRCLE)
            } else if (checkedId == R.id.rbcross) {
                sideSelected(AppConstants.TEAM_CROSS)
            }
        }



        CysNxtBtn.setOnClickListener {
            if (yourSide.isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.choose_a_side), Toast.LENGTH_SHORT).show()
            } else {
                goToGameActivity()
            }
        }

        CysBackBtn.setOnClickListener {
            finish()
        }


    }


    private fun sideSelected(selectedSideValue: String) {
        yourSide = selectedSideValue
        txtshowchoose.text = "${getString(R.string.your_side)} $yourSide"
        Toast.makeText(this, getString(R.string.you_are_all_set), Toast.LENGTH_SHORT).show()


        CysNxtBtn.apply {
            background = getDrawable(R.drawable.ic_play_button_shape)
            text = getString(R.string.play)
            setTextColor(getColor(R.color.white))
        }


    }

    private fun goToGameActivity() {
        startActivity(Intent(this@ChooseYourSideActivity, GameActivity1::class.java).apply {
            putExtra(AppConstants.USERNAME, username)
            putExtra(AppConstants.USER_TEAM, yourSide)
            putExtra(AppConstants.FRiEND_NAME, friendName)
        })


    }


}

