package com.dhimandasgupta.vectorplay.dream

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Handler
import android.os.Looper
import android.service.dreams.DreamService
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import java.util.Calendar

private val DIGIT_STATES = intArrayOf(
    R.attr.state_zero,
    R.attr.state_one,
    R.attr.state_two,
    R.attr.state_three,
    R.attr.state_four,
    R.attr.state_five,
    R.attr.state_six,
    R.attr.state_seven,
    R.attr.state_eight,
    R.attr.state_nine
)

private const val DIM_HIGHEST = 0x50FFFFFF
private const val DIM_MODERATE = 0x70FFFFFF

class ClockService : DreamService(), Runnable {
    private lateinit var imageViewHourFirst: AppCompatImageView
    private lateinit var imageViewHourSecond: AppCompatImageView

    private lateinit var imageViewMinuteFirst: AppCompatImageView
    private lateinit var imageViewMinuteSecond: AppCompatImageView

    private lateinit var imageViewSecondFirst: AppCompatImageView
    private lateinit var imageViewSecondSecond: AppCompatImageView

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setContentView(R.layout.dream_clock)
    }

    override fun onDreamingStarted() {
        super.onDreamingStarted()

        isInteractive = false
        isFullscreen = true

        imageViewHourFirst = findViewById(R.id.hour_first)
        imageViewHourSecond =  findViewById(R.id.hour_second)

        imageViewMinuteFirst =  findViewById(R.id.minute_first)
        imageViewMinuteSecond =  findViewById(R.id.minute_second)

        imageViewSecondFirst =  findViewById(R.id.second_first)
        imageViewSecondSecond =  findViewById(R.id.second_second)

        run {
            imageViewHourFirst.dimView()
            imageViewHourSecond.dimView()
            imageViewMinuteFirst.dimView()
            imageViewMinuteSecond.dimView()
            imageViewSecondFirst.dimView(DIM_HIGHEST)
            imageViewSecondSecond.dimView(DIM_HIGHEST)
        }

        handler.post(this)
    }

    override fun onDreamingStopped() {
        super.onDreamingStopped()

        handler.removeCallbacksAndMessages(this)
    }

    override fun run() {
        stateClock()

        handler.postDelayed(this, 1000)
    }

    private fun stateClock() {
        val calendar = Calendar.getInstance()
        val hours: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes: Int = calendar.get(Calendar.MINUTE)
        val seconds: Int = calendar.get(Calendar.SECOND)

        imageViewSecondSecond.setImageState(getState(seconds.rem(10)), true)
        imageViewSecondFirst.setImageState(getState(seconds.div(10)), true)

        imageViewMinuteSecond.setImageState(getState(minutes.rem(10)), true)
        imageViewMinuteFirst.setImageState(getState(minutes.div(10)), true)

        imageViewHourSecond.setImageState(getState(hours.rem(10)), true)
        imageViewHourFirst.setImageState(getState(hours.div(10)), true)
    }

    private fun getState(digit: Int): IntArray {
        val stateSet = IntArray(1)
        stateSet[0] = if (digit <= DIGIT_STATES.size - 1) DIGIT_STATES[digit] else DIGIT_STATES[0]
        return stateSet
    }
}

// This dims a View with specified amount.
fun View.dimView(dimAmount: Int = DIM_MODERATE) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = Color.WHITE
    paint.colorFilter = PorterDuffColorFilter(dimAmount, PorterDuff.Mode.MULTIPLY)
    this.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
}