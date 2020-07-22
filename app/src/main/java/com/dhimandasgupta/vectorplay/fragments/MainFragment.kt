package com.dhimandasgupta.vectorplay.fragments

import android.os.Bundle
import android.view.View
import com.dhimandasgupta.vectorplay.R
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment() {
    companion object {
        fun fragment() = MainFragment()
    }

    override fun getLayoutId(): Int  = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.one.setOnClickListener {
            callbacks?.onFirstClicked()
        }

        view.two.setOnClickListener {
            callbacks?.onSecondClicked()
        }

        view.three.setOnClickListener {
            callbacks?.onThirdClicked()
        }

        view.fourth.setOnClickListener {
            callbacks?.onFourthClicked()
        }

        view.fifth.setOnClickListener {
            callbacks?.onFifthClicked()
        }

        view.sixth.setOnClickListener {
            callbacks?.onSixthClicked()
        }
    }
}