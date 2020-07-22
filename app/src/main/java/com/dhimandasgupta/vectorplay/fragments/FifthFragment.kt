package com.dhimandasgupta.vectorplay.fragments

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_fifth.view.*

class FifthFragment : BaseFragment() {
    private lateinit var ioImageView: AppCompatImageView
    private lateinit var handWrittenImageView: AppCompatImageView

    private lateinit var ioAnimatable: Animatable
    private lateinit var handWrittenAnimatable: Animatable

    companion object {
        fun fragment() = FifthFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_fifth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ioImageView = view.io_image
        handWrittenImageView = view.hand_written_image

        ioAnimatable = ioImageView.drawable as Animatable
        handWrittenAnimatable = handWrittenImageView.drawable as Animatable

        view.setOnClickListener {
            restartAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        ioAnimatable.stop()
        handWrittenAnimatable.stop()
    }

    private fun restartAnimation() {
        ioAnimatable.stop()
        ioAnimatable.start()

        handWrittenAnimatable.stop()
        handWrittenAnimatable.start()
    }
}