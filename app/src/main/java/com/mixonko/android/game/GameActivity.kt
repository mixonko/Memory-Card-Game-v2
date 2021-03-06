package com.mixonko.android.game

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.os.Vibrator
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.SystemClock
import android.preference.PreferenceManager
import android.view.View.TRANSLATION_X
import android.view.View.TRANSLATION_Y
import android.view.animation.*
import android.widget.*
import com.game.memory.card.game.R
import java.lang.Exception
import android.os.CountDownTimer
import java.text.SimpleDateFormat
import java.util.*


class GameActivity : AppCompatActivity() {

    private val cardBack = R.drawable.slot

    private val image11 = R.drawable.ic_image11
    private val image12 = R.drawable.ic_image12
    private val image13 = R.drawable.ic_image13
    private val image14 = R.drawable.ic_image14
    private val image15 = R.drawable.ic_image15
    private val image16 = R.drawable.ic_image16
    private val image17 = R.drawable.ic_image17
    private val image18 = R.drawable.ic_image18
    private val image21 = R.drawable.ic_image21
    private val image22 = R.drawable.ic_image22
    private val image23 = R.drawable.ic_image23
    private val image24 = R.drawable.ic_image24
    private val image25 = R.drawable.ic_image25
    private val image26 = R.drawable.ic_image26
    private val image27 = R.drawable.ic_image27
    private val image28 = R.drawable.ic_image28

    private lateinit var imageView11: ImageView
    private lateinit var imageView12: ImageView
    private lateinit var imageView13: ImageView
    private lateinit var imageView14: ImageView
    private lateinit var imageView21: ImageView
    private lateinit var imageView22: ImageView
    private lateinit var imageView23: ImageView
    private lateinit var imageView24: ImageView
    private lateinit var imageView31: ImageView
    private lateinit var imageView32: ImageView
    private lateinit var imageView33: ImageView
    private lateinit var imageView34: ImageView
    private lateinit var imageView41: ImageView
    private lateinit var imageView42: ImageView
    private lateinit var imageView43: ImageView
    private lateinit var imageView44: ImageView

    private lateinit var gameOverView: FrameLayout
    private lateinit var winGameView: FrameLayout
    private lateinit var winGameButton: ImageButton
    private lateinit var gameOverButton: ImageButton

    private lateinit var currentTimeTextView: TextView
    private lateinit var bestTimeTextView: TextView

    private lateinit var line1: LinearLayout
    private lateinit var line2: LinearLayout
    private lateinit var line3: LinearLayout
    private lateinit var line4: LinearLayout

    private var firstCard: Int? = 0
    private var secondCard: Int? = 0

    private var clickedFirst: Int? = 0
    private var clickedSecond: Int? = 0

    private var cardNumber: Int = 1

    private var cardsArray =
        mutableListOf(11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28)

    private lateinit var settings: SharedPreferences
    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayerButtonsClick: MediaPlayer

    private lateinit var soundPlayerDone: MediaPlayer
    private lateinit var soundPlayerNot: MediaPlayer

    private lateinit var firstImage: ImageView
    private lateinit var secondImage: ImageView

    private lateinit var chronometer: Chronometer
    private lateinit var countDownTimer: CountDownTimer
    private var countDownTimerIsRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        sp = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        settings = PreferenceManager.getDefaultSharedPreferences(this)

        findViewById()

        startLayoutAnimation()

        setTag()

        Collections.shuffle(cardsArray)

        setOnClickListener()

    }

    private fun startLayoutAnimation() {
        val bounceInterpolator = BounceInterpolator()
        val objectAnimator1 = ObjectAnimator.ofFloat(line1, TRANSLATION_Y, -800f, 0f)
        objectAnimator1.setInterpolator(bounceInterpolator)
        objectAnimator1.setDuration(900).start()

        val objectAnimator2 = ObjectAnimator.ofFloat(line2, TRANSLATION_Y, -400f, 0f)
        objectAnimator2.setInterpolator(bounceInterpolator)
        objectAnimator2.setDuration(900).start()

        val objectAnimator3 = ObjectAnimator.ofFloat(line3, TRANSLATION_Y, 400f, 0f)
        objectAnimator3.setInterpolator(bounceInterpolator)
        objectAnimator3.setDuration(900).start()

        val objectAnimator4 = ObjectAnimator.ofFloat(line4, TRANSLATION_Y, 800f, 0f)
        objectAnimator4.setInterpolator(bounceInterpolator)
        objectAnimator4.setDuration(900).start()

        val linearInterpolator = LinearInterpolator()
        val objectAnimator5 = ObjectAnimator.ofFloat(chronometer, TRANSLATION_X, 800f, 0f)
        objectAnimator5.setInterpolator(linearInterpolator)
        objectAnimator5.setDuration(400).start()
    }

    private fun setTag() {
        imageView11.setTag(0)
        imageView12.setTag(1)
        imageView13.setTag(2)
        imageView14.setTag(3)
        imageView21.setTag(4)
        imageView22.setTag(5)
        imageView23.setTag(6)
        imageView24.setTag(7)
        imageView31.setTag(8)
        imageView32.setTag(9)
        imageView33.setTag(10)
        imageView34.setTag(11)
        imageView41.setTag(12)
        imageView42.setTag(13)
        imageView43.setTag(14)
        imageView44.setTag(15)
    }

    private fun findViewById() {
        imageView11 = findViewById(R.id.imageView11)
        imageView12 = findViewById(R.id.imageView12)
        imageView13 = findViewById(R.id.imageView13)
        imageView14 = findViewById(R.id.imageView14)
        imageView21 = findViewById(R.id.imageView21)
        imageView22 = findViewById(R.id.imageView22)
        imageView23 = findViewById(R.id.imageView23)
        imageView24 = findViewById(R.id.imageView24)
        imageView31 = findViewById(R.id.imageView31)
        imageView32 = findViewById(R.id.imageView32)
        imageView33 = findViewById(R.id.imageView33)
        imageView34 = findViewById(R.id.imageView34)
        imageView41 = findViewById(R.id.imageView41)
        imageView42 = findViewById(R.id.imageView42)
        imageView43 = findViewById(R.id.imageView43)
        imageView44 = findViewById(R.id.imageView44)
        gameOverView = findViewById(R.id.game_over_view)
        winGameView = findViewById(R.id.win_game_view)
        winGameButton = findViewById(R.id.win_game_button)
        gameOverButton = findViewById(R.id.game_over_button)

        currentTimeTextView = findViewById(R.id.current_time)
        bestTimeTextView = findViewById(R.id.best_time)

        line1 = findViewById(R.id.line_1)
        line2 = findViewById(R.id.line_2)
        line3 = findViewById(R.id.line_3)
        line4 = findViewById(R.id.line_4)

        chronometer = findViewById(R.id.chronometer)
    }

    private fun setOnClickListener() {
        imageView11.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView11, theCard)
        }
        imageView12.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView12, theCard)
        }
        imageView13.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView13, theCard)
        }
        imageView14.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView14, theCard)
        }
        imageView21.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView21, theCard)
        }
        imageView22.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView22, theCard)
        }
        imageView23.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView23, theCard)
        }
        imageView24.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView24, theCard)
        }
        imageView31.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView31, theCard)
        }
        imageView32.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView32, theCard)
        }
        imageView33.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView33, theCard)
        }
        imageView34.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView34, theCard)
        }
        imageView41.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView41, theCard)
        }
        imageView42.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView42, theCard)
        }
        imageView43.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView43, theCard)
        }
        imageView44.setOnClickListener {
            val theCard = it.getTag().toString().toInt()
            onImageClick(imageView44, theCard)
        }
        winGameButton.setOnClickListener {
            startNewGame()
        }
        gameOverButton.setOnClickListener {
            startNewGame()
        }
    }

    private fun startNewGame() {
        startButtonSound()
        startVibration()
        startActivity(Intent(this, GameActivity::class.java))
        finish()
    }

    private fun startButtonSound() {
        if (musicCheck()) {
            mediaPlayerButtonsClick = MediaPlayer.create(
                this,
                R.raw.buttons_sound
            )
            mediaPlayerButtonsClick.start()
        }
    }

    private fun startChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    private fun stopChronometer() {
        chronometer.stop()
    }

    private fun startCountDownTimer() {
        countDownTimerIsRunning = true
        val down: TextView = findViewById(R.id.down)
        countDownTimer = object : CountDownTimer(120000, 10) {

            override fun onTick(millisUntilFinished: Long) {
                val mTimeFormat = SimpleDateFormat("mm:ss:SS")
                down.setText(mTimeFormat.format(Date(millisUntilFinished)))
            }

            override fun onFinish() {
                down.setText(R.string.time_is_over)
                startGameOverAnimation()
                showGameOver()

            }
        }.start()
    }

    private fun stopCountDownTimer() {
        countDownTimerIsRunning = false
        countDownTimer.cancel()
    }

    private fun showCardImage(imageView: ImageView, image: Int) {
        imageView.animate().withLayer()
            .rotationY(90f)
            .setDuration(150)
            .withEndAction(
                Runnable() {
                    imageView.setImageResource(image)

                    imageView.setRotationY(-90f)
                    imageView.animate().withLayer()
                        .rotationY(0f)
                        .setDuration(150)
                        .start()
                }
            ).start()
    }

    private fun startGameOverAnimation() {
        val images: LinearLayout = findViewById(R.id.images)
        images.animate().withLayer()
            .scaleX(0.001f)
            .scaleY(0.001f)
            .setDuration(1000)
            .start()
    }

    private fun showBackCardImage(vararg imageView: ImageView) {
        imageView.forEach {
            it.animate().withLayer()
                .rotationY(90f)
                .setDuration(150)
                .withEndAction(
                    Runnable() {
                        it.setImageResource(cardBack)

                        it.setRotationY(-90f)
                        it.animate().withLayer()
                            .rotationY(0f)
                            .setDuration(150)
                            .start()
                    }
                ).start()
        }

    }

    private fun startDoneAnimation(vararg imageView: ImageView) {
        imageView.forEach {
            it.animate().withLayer()
                .scaleX(0.1f)
                .scaleXBy(0.115f)
                .scaleY(0.1f)
                .scaleYBy(0.115f)
                .setDuration(500)
                .withEndAction(
                    Runnable() {
                        it.animate().withLayer()
                            .scaleX(0.001f)
                            .scaleY(0.001f)
                            .setDuration(300)
                            .start()
                    }
                ).start()
        }
    }

    private fun startNotAnimation(vararg imageView: ImageView) {
        imageView.forEach {
            it.animate().withLayer()
                .rotationY(40f)
                .setDuration(150)
                .withEndAction(
                    Runnable() {
                        it.setRotationY(-40f)
                        it.animate().withLayer()
                            .rotationY(0f)
                            .setDuration(150)
                            .start()
                    }
                ).start()
        }
    }

    private fun onImageClick(imageView: ImageView, card: Int) {
        startVibration()
        if (!chronometer.isRunning()) {
            startChronometer()
        }
        if (!countDownTimerIsRunning) {
            startCountDownTimer()
        }
        when (cardsArray[card]) {
            11 -> showCardImage(imageView, image11)
            12 -> showCardImage(imageView, image12)
            13 -> showCardImage(imageView, image13)
            14 -> showCardImage(imageView, image14)
            15 -> showCardImage(imageView, image15)
            16 -> showCardImage(imageView, image16)
            17 -> showCardImage(imageView, image17)
            18 -> showCardImage(imageView, image18)
            21 -> showCardImage(imageView, image21)
            22 -> showCardImage(imageView, image22)
            23 -> showCardImage(imageView, image23)
            24 -> showCardImage(imageView, image24)
            25 -> showCardImage(imageView, image25)
            26 -> showCardImage(imageView, image26)
            27 -> showCardImage(imageView, image27)
            28 -> showCardImage(imageView, image28)

        }

        if (cardNumber == 1) {
            firstImage = imageView
            firstCard = cardsArray[card]
            if (firstCard!! > 20) {
                firstCard = firstCard!! - 10
            }

            cardNumber = 2
            clickedFirst = card

            imageView.isEnabled = false

        } else if (cardNumber == 2) {
            secondImage = imageView
            secondCard = cardsArray[card]
            if (secondCard!! > 20) {
                secondCard = secondCard!! - 10
            }
            cardNumber = 1
            clickedSecond = card

            imageViewIsEnabled(false)

            if (firstCard == secondCard) {
                startSoundDone()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    startDoneAnimation(firstImage, secondImage)
                }, 300)


            } else {
                startSoundNot()
                val handler = Handler()
                handler.postDelayed(Runnable {
                    startNotAnimation(firstImage, secondImage)
                }, 300)

            }

            val handler = Handler()
            handler.postDelayed(Runnable {
                calculate()
            }, 1300)

        }

    }

    private fun calculate() {
        if (firstCard == secondCard) {
            when (clickedFirst) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }

            when (clickedSecond) {
                0 -> imageView11.visibility = View.INVISIBLE
                1 -> imageView12.visibility = View.INVISIBLE
                2 -> imageView13.visibility = View.INVISIBLE
                3 -> imageView14.visibility = View.INVISIBLE
                4 -> imageView21.visibility = View.INVISIBLE
                5 -> imageView22.visibility = View.INVISIBLE
                6 -> imageView23.visibility = View.INVISIBLE
                7 -> imageView24.visibility = View.INVISIBLE
                8 -> imageView31.visibility = View.INVISIBLE
                9 -> imageView32.visibility = View.INVISIBLE
                10 -> imageView33.visibility = View.INVISIBLE
                11 -> imageView34.visibility = View.INVISIBLE
                12 -> imageView41.visibility = View.INVISIBLE
                13 -> imageView42.visibility = View.INVISIBLE
                14 -> imageView43.visibility = View.INVISIBLE
                15 -> imageView44.visibility = View.INVISIBLE
            }

        } else {
            showBackCardImage(firstImage, secondImage)

        }
        imageViewIsEnabled(true)

        checkEndGame()

    }

    private fun startVibration() {
        if (vibrationCheck()) {
            val vibe = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(40)
        }
    }

    private fun startSoundDone() {
        if (musicCheck()) {
            soundPlayerDone = MediaPlayer.create(
                this,
                R.raw.done
            )
            soundPlayerDone.start()
        }
    }

    private fun startSoundNot() {
        if (musicCheck()) {
            soundPlayerNot = MediaPlayer.create(this, R.raw.not)
            soundPlayerNot.start()
        }
    }

    private fun musicCheck(): Boolean = settings.getBoolean("music", true)

    private fun vibrationCheck(): Boolean = settings.getBoolean("vibration", true)

    private fun imageViewIsEnabled(b: Boolean) {
        imageView11.isEnabled = b
        imageView12.isEnabled = b
        imageView13.isEnabled = b
        imageView14.isEnabled = b
        imageView21.isEnabled = b
        imageView22.isEnabled = b
        imageView23.isEnabled = b
        imageView24.isEnabled = b
        imageView31.isEnabled = b
        imageView32.isEnabled = b
        imageView33.isEnabled = b
        imageView34.isEnabled = b
        imageView41.isEnabled = b
        imageView42.isEnabled = b
        imageView43.isEnabled = b
        imageView44.isEnabled = b
    }

    private fun checkEndGame() {
        if (imageView11.visibility == View.INVISIBLE
            && imageView12.visibility == View.INVISIBLE &&
            imageView13.visibility == View.INVISIBLE && imageView14.visibility == View.INVISIBLE &&
            imageView21.visibility == View.INVISIBLE && imageView22.visibility == View.INVISIBLE &&
            imageView23.visibility == View.INVISIBLE && imageView24.visibility == View.INVISIBLE &&
            imageView31.visibility == View.INVISIBLE && imageView32.visibility == View.INVISIBLE &&
            imageView33.visibility == View.INVISIBLE && imageView34.visibility == View.INVISIBLE &&
            imageView41.visibility == View.INVISIBLE && imageView42.visibility == View.INVISIBLE &&
            imageView43.visibility == View.INVISIBLE && imageView44.visibility == View.INVISIBLE
        ) {
            stopChronometer()
            stopCountDownTimer()
            showWinGame()
        }

    }

    private fun showWinGame() {
        winGameView.visibility = View.VISIBLE

        currentTimeTextView.setText(chronometer.text.toString())
        val currentTime: Long = chronometer.timeElapsed
        var bestTime: Long = sp.getLong(TIME, 0L)
        if (bestTime == 0L) {
            bestTime = currentTime
        }
        if (currentTime < bestTime || currentTime == bestTime) {
            bestTimeTextView.setText(chronometer.text.toString())
            saveInfo(currentTime, chronometer.text.toString())
        }
        if (currentTime > bestTime) {
            bestTimeTextView.setText(sp.getString(TIME_TEXT, "0"))
            saveInfo(bestTime, bestTimeTextView.text.toString())
        }
    }

    private fun showGameOver() {
        gameOverView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        if (musicCheck()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music2)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        } else try {
            mediaPlayer.stop()
        } catch (e: Exception) {
        }
    }

    override fun onPause() {
        super.onPause()

        try {
            mediaPlayer.stop()
            soundPlayerNot.stop()
            soundPlayerDone.stop()
        } catch (e: Exception) {
        }
    }

    private fun saveInfo(bestTime: Long, bestTimeText: String) {
        editor = sp.edit()
        editor.putLong(TIME, bestTime)
        editor.putString(TIME_TEXT, bestTimeText)
        editor.apply()
    }

}

