package com.dhimandasgupta.vectorplay.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dhimandasgupta.vectorplay.Callbacks

abstract class BaseFragment : Fragment() {
    var callbacks: Callbacks? = null

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Callbacks) {
            callbacks = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onDetach() {
        super.onDetach()

        callbacks = null
    }
}