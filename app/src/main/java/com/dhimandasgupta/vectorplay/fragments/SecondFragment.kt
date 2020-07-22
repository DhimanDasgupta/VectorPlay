package com.dhimandasgupta.vectorplay.fragments

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : BaseFragment() {
    private lateinit var checkBox: AppCompatCheckBox
    private lateinit var imageView: AppCompatImageView

    companion object {
        fun fragment() = SecondFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_second

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox = view.animated_check_box
        imageView = view.animated_image_view

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) startLaugh() else stopLaugh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        stopLaugh()
    }

    private fun startLaugh() {
        val drawable: Drawable = imageView.drawable
        if (drawable is Animatable) {
            (drawable as Animatable).stop()
            (drawable as Animatable).start()
        }
        checkBox.text = "Stop Laughing"
    }

    private fun stopLaugh() {
        val drawable: Drawable = imageView.drawable
        if (imageView.drawable is Animatable) {
            (drawable as Animatable).stop()
        }
        checkBox.text = "Start Laughing"
    }
}