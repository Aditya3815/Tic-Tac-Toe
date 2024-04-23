package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.ui.GameResultActivity
import utils.AppConstants
import utils.GameRules

class GameActivity1 : AppCompatActivity(), OnClickListener {
    private var username: String? = null
    private var userside: String? = null
    private var friendName: String? = null

    private lateinit var ivCloseGameScreen: ImageButton
    private lateinit var tvFriendOrAISteps: TextView
    private lateinit var tvFriendOrAIName: TextView
    private lateinit var ivFirst: ImageView
    private lateinit var ivSecond: ImageView
    private lateinit var ivThird: ImageView
    private lateinit var ivFour: ImageView
    private lateinit var ivFive: ImageView
    private lateinit var ivSixth: ImageView
    private lateinit var ivSeventh: ImageView
    private lateinit var ivEight: ImageView
    private lateinit var ivNineth: ImageView
    private lateinit var tvUser: TextView
    private lateinit var ivUser: ImageView
    private lateinit var tvUserSteps: TextView
    private lateinit var ivFriendOrAI: ImageView
    private lateinit var rlFriendOrAIContainer: ConstraintLayout
    private lateinit var rlUserContainer: ConstraintLayout
    private lateinit var tvGameResult: TextView
    private var userSideMapping = Pair(-1, "")

    private var playingWithFriend: Boolean = false
    private val imageBoxes =
        listOf(
            R.id.ivFirst,
            R.id.ivSecond,
            R.id.ivThird,
            R.id.ivFourth,
            R.id.ivFive,
            R.id.ivSixth,
            R.id.ivSeventh,
            R.id.ivEighth,
            R.id.ivNineth
        )

    private val player1Filled = 0
    private val playerFriendOrAI = 1
    val filledPositions = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

    private val player_0 = 0
    private val player_1 = 1

    private var isGameInProgress = false
    private var activePlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game1)

        initialiseViews()

    }

    private fun initialiseViews() {

        ivCloseGameScreen = findViewById(R.id.gaBackBtn)
        tvFriendOrAISteps = findViewById(R.id.tvFriendOrAISteps)
        tvFriendOrAIName = findViewById(R.id.tvFriendOrAIName)
        ivFirst = findViewById(R.id.ivFirst)
        ivSecond = findViewById(R.id.ivSecond)
        ivThird = findViewById(R.id.ivThird)
        ivFour = findViewById(R.id.ivFourth)
        ivFive = findViewById(R.id.ivFive)
        ivSixth = findViewById(R.id.ivSixth)
        ivSeventh = findViewById(R.id.ivSeventh)
        ivEight = findViewById(R.id.ivEighth)
        ivNineth = findViewById(R.id.ivNineth)
        tvUser = findViewById(R.id.tvUserName)
        ivUser = findViewById(R.id.ivUser)
        tvUserSteps = findViewById(R.id.tvUserSteps)
        ivFriendOrAI = findViewById(R.id.ivFriendOrAI)
        rlFriendOrAIContainer = findViewById(R.id.rlFriendOrAIContainer)
        rlUserContainer = findViewById(R.id.rlUserContainer)
        tvGameResult = findViewById(R.id.tvGameResult)
        setupUI()
        setUpClickListeners()
    }

    private fun setupUI() {
        username = intent?.getStringExtra(AppConstants.USERNAME)
        userside = intent?.getStringExtra(AppConstants.USER_TEAM)
        friendName = intent?.getStringExtra(AppConstants.FRiEND_NAME)

        userside?.also {
            if (it == AppConstants.TEAM_CIRCLE) {
                activePlayer = 0
                userSideMapping = Pair(0, "USER")
            } else if (it == AppConstants.TEAM_CROSS) {
                activePlayer = 1
                userSideMapping = Pair(1, "USER")
            }
        }

        username.also {
            tvUser.text = it
        }

        friendName?.also {
            playingWithFriend = true
            tvFriendOrAIName.text = it
        }


        val image = if (playingWithFriend) R.drawable.twotone_face_24 else R.drawable.robot
        ivFriendOrAI.setImageDrawable(getDrawable(image))

        rlUserContainer.background = getDrawable(R.drawable.ic_active_indicator)
    }

    private fun setUpClickListeners() {
        ivCloseGameScreen.setOnClickListener {
            finish()
        }

        ivFirst.setOnClickListener(this)
        ivSecond.setOnClickListener(this)
        ivThird.setOnClickListener(this)
        ivFour.setOnClickListener(this)
        ivFive.setOnClickListener(this)
        ivSixth.setOnClickListener(this)
        ivSeventh.setOnClickListener(this)
        ivEight.setOnClickListener(this)
        ivNineth.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        Log.d(TAG, "Coming_HERE_Inside_onClick")
        val clickedImage = findViewById<ImageView>(view.id)
        val clickedImageTag = view.tag?.toString()?.toInt() ?: 0
        val position = clickedImageTag - 1
        Log.d(TAG, "Clicked Image TAG = $clickedImageTag")

        if (activePlayer == player_0 && filledPositions[position] == -1) {
            clickedImage.setImageDrawable(getDrawable(R.drawable.outline_circle_24))

            filledPositions[position] = player_0


            if (GameRules.checkForWin(filledPositions)) {
                gameComplete(activePlayer)
            } else {
                activePlayer = player_1
                makeContainerActive(
                    activeViewGroup = rlUserContainer,
                    disableViewGroup = rlFriendOrAIContainer
                )
            }

        } else if (activePlayer == player_1 && filledPositions[position] == -1) {
            clickedImage.setImageDrawable(getDrawable(R.drawable.baseline_clear_24))

            filledPositions[position] = player_1


            if (GameRules.checkForWin(filledPositions)) {
                gameComplete(activePlayer)
            } else {
                activePlayer = player_0
                makeContainerActive(
                    activeViewGroup = rlFriendOrAIContainer,
                    disableViewGroup = rlUserContainer
                )
            }
        }
    }

    private fun gameComplete(activePlayer: Int) {
        Log.d(TAG, "Coming_Inside_gameComplete $activePlayer")
        Log.d(TAG, "Coming_Inside_gameComplete $userSideMapping")

        val whoWon = if (userSideMapping.first == activePlayer) {
            AppConstants.user
        } else {
            AppConstants.friendOrAI
        }

        val winnerName = if ((whoWon == AppConstants.user) && !username.isNullOrEmpty()) {
            username
        } else if ((whoWon == AppConstants.friendOrAI) && !friendName.isNullOrEmpty()) {
            friendName
        } else {
            "NA"
        }

        if (tvGameResult != null) {
            tvGameResult.text = "$winnerName ${getString(R.string.game_won)}"
        }

        Log.d("GameComplete", "Game completed, winner: $winnerName")

        val intent = Intent(this@GameActivity1, GameResultActivity::class.java).apply {
            putExtra(AppConstants.WINNER_MESSAGE, "$winnerName ${getString(R.string.game_won)}")
        }
        startActivity(intent)

    }



    fun makeContainerActive(activeViewGroup: ConstraintLayout, disableViewGroup: ConstraintLayout) {
        disableViewGroup.background = null
        activeViewGroup.background = getDrawable(R.drawable.ic_active_indicator)
    }

    companion object {
        private val TAG = GameActivity1::class.java.simpleName
    }
}