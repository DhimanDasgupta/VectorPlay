package com.dhimandasgupta.vectorplay.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_fourth.view.*

private val STATE_SET_HIPPO =
    intArrayOf(R.attr.state_hippo, -R.attr.state_buffalo, -R.attr.state_elephant)
private val STATE_SET_BUFFALO =
    intArrayOf(-R.attr.state_hippo, R.attr.state_buffalo, -R.attr.state_elephant)
private val STATE_SET_ELEPHANT =
    intArrayOf(-R.attr.state_hippo, -R.attr.state_buffalo, R.attr.state_elephant)

class FourthFragment : BaseFragment() {
    private lateinit var imageView: AppCompatImageView

    private lateinit var hippoButton: AppCompatButton
    private lateinit var buffaloButton: AppCompatButton
    private lateinit var elephantButton: AppCompatButton

    companion object {
        fun fragment() = FourthFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_fourth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.animal_image

        hippoButton = view.hippo
        buffaloButton = view.buffalo
        elephantButton = view.elephant

        hippoButton.setOnClickListener {
            hippoButton.isEnabled = false
            buffaloButton.isEnabled = true
            elephantButton.isEnabled = true
            imageView.setImageState(STATE_SET_HIPPO, true)
        }

        buffaloButton.setOnClickListener {
            hippoButton.isEnabled = true
            buffaloButton.isEnabled = false
            elephantButton.isEnabled = true
            imageView.setImageState(STATE_SET_BUFFALO, true)
        }

        elephantButton.setOnClickListener {
            hippoButton.isEnabled = true
            buffaloButton.isEnabled = true
            elephantButton.isEnabled = false
            imageView.setImageState(STATE_SET_ELEPHANT, true)
        }
    }
}