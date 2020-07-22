package com.dhimandasgupta.vectorplay.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_sixth.view.*

class SixthFragment : BaseFragment() {
    private lateinit var airplaneImageView: AppCompatImageView
    private lateinit var eyeImageView: AppCompatImageView
    private lateinit var flashImageView: AppCompatImageView
    private lateinit var searchBackImageView: AppCompatImageView
    private lateinit var heartImageView: AppCompatImageView

    private lateinit var drawerImageView: AppCompatImageView
    private lateinit var crossTickImageView: AppCompatImageView
    private lateinit var plusMinusImageView: AppCompatImageView
    private lateinit var arrowOverflowImageView: AppCompatImageView

    private var isChecked = false

    companion object {
        fun fragment() = SixthFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_sixth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        airplaneImageView = view.airplane
        eyeImageView = view.eye
        flashImageView = view.flashlight
        searchBackImageView = view.searchback
        heartImageView = view.heart

        drawerImageView = view.drawer
        crossTickImageView = view.crosstick
        plusMinusImageView = view.plusminus
        arrowOverflowImageView = view.arrowoverflow

        view.setOnClickListener {
            checkChanged()
        }
    }

    private fun checkChanged() {
        isChecked = !isChecked
        val stateSet =
            intArrayOf(android.R.attr.state_checked * if (isChecked) 1 else -1)

        airplaneImageView.setImageState(stateSet, true)
        eyeImageView.setImageState(stateSet, true)
        flashImageView.setImageState(stateSet, true)
        searchBackImageView.setImageState(stateSet, true)
        heartImageView.setImageState(stateSet, true)

        drawerImageView.setImageState(stateSet, true)
        crossTickImageView.setImageState(stateSet, true)
        plusMinusImageView.setImageState(stateSet, true)
        arrowOverflowImageView.setImageState(stateSet, true)
    }
}