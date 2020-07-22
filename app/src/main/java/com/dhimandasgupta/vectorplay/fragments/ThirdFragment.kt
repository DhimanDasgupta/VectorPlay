package com.dhimandasgupta.vectorplay.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_thrird.view.*
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

class ThirdFragment : BaseFragment(), Runnable {
    private lateinit var imageViewHourFirst: AppCompatImageView
    private lateinit var imageViewHourSecond: AppCompatImageView

    private lateinit var imageViewMinuteFirst: AppCompatImageView
    private lateinit var imageViewMinuteSecond: AppCompatImageView

    private lateinit var imageViewSecondFirst: AppCompatImageView
    private lateinit var imageViewSecondSecond: AppCompatImageView

    private lateinit var checkBox: AppCompatCheckBox

    private val handler: Handler = Handler(Looper.getMainLooper())

    companion object {
        fun fragment() = ThirdFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_thrird

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewHourFirst = view.hour_first
        imageViewHourSecond = view.hour_second

        imageViewMinuteFirst = view.minute_first
        imageViewMinuteSecond = view.minute_second

        imageViewSecondFirst = view.second_first
        imageViewSecondSecond = view.second_second

        checkBox = view.checkbox

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                imageViewSecondFirst.visibility = View.VISIBLE
                imageViewSecondSecond.visibility = View.VISIBLE
            } else {
                imageViewSecondFirst.visibility = View.GONE
                imageViewSecondSecond.visibility = View.GONE
            }
        }

        handler.post(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

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