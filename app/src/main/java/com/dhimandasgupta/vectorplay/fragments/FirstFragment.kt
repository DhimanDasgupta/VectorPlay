package com.dhimandasgupta.vectorplay.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.graphics.drawable.DrawableCompat
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : BaseFragment(), OnSeekBarChangeListener, OnPreDrawListener {
    private lateinit var seekbar: AppCompatSeekBar
    private lateinit var imageView: AppCompatImageView

    private var imageMaxEachSide = 0

    companion object {
        fun fragment() = FirstFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekbar = view.scale_tint_seek_bar
        imageView = view.scale_tint_image_view

        seekbar.setOnSeekBarChangeListener(this)
        imageView.viewTreeObserver.addOnPreDrawListener(this)

        view.scale_tint_red_button.setOnClickListener {
            tintColor(Color.RED)
        }
        view.scale_tint_green_button.setOnClickListener {
            tintColor(Color.GREEN)
        }
        view.scale_tint_blue_button.setOnClickListener {
            tintColor(Color.BLUE)
        }
        view.scale_tint_gray_button.setOnClickListener {
            tintColor(Color.GRAY)
        }
        view.scale_tint_magenta_button.setOnClickListener {
            tintColor(Color.MAGENTA)
        }
        view.scale_tint_cyan_button.setOnClickListener {
            tintColor(Color.CYAN)
        }
        view.scale_tint_black_button.setOnClickListener {
            tintColor(Color.BLACK)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val currentSize = imageMaxEachSide * progress / (seekBar?.max ?: 1)

        imageView.layoutParams.width = currentSize
        imageView.layoutParams.height = currentSize

        imageView.requestLayout()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun onPreDraw(): Boolean {
        val imageMaxWidth: Int = imageView.width
        val imageMaxHeight: Int = imageView.height

        imageMaxEachSide = if (imageMaxWidth < imageMaxHeight) imageMaxWidth else imageMaxHeight

        imageView.viewTreeObserver.removeOnPreDrawListener(this)

        return true
    }

    private fun tintColor(@ColorInt color: Int) {
        val drawable: Drawable = imageView.drawable
        var wrappedDrawable = DrawableCompat.wrap(drawable)
        wrappedDrawable = wrappedDrawable.mutate()
        DrawableCompat.setTint(wrappedDrawable, color)
    }
}