package com.dhimandasgupta.vectorplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhimandasgupta.vectorplay.fragments.BaseFragment
import com.dhimandasgupta.vectorplay.fragments.FifthFragment
import com.dhimandasgupta.vectorplay.fragments.FourthFragment
import com.dhimandasgupta.vectorplay.fragments.MainFragment
import com.dhimandasgupta.vectorplay.fragments.FirstFragment
import com.dhimandasgupta.vectorplay.fragments.ThirdFragment
import com.dhimandasgupta.vectorplay.fragments.SecondFragment
import com.dhimandasgupta.vectorplay.fragments.SixthFragment

class MainActivity : AppCompatActivity(), Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(MainFragment.fragment())
        }
    }

    override fun onFirstClicked() {
        replaceFragment(FirstFragment.fragment())
    }

    override fun onSecondClicked() {
        replaceFragment(SecondFragment.fragment())
    }

    override fun onThirdClicked() {
        replaceFragment(ThirdFragment.fragment())
    }

    override fun onFourthClicked() {
        replaceFragment(FourthFragment.fragment())
    }

    override fun onFifthClicked() {
        replaceFragment(FifthFragment.fragment())
    }

    override fun onSixthClicked() {
        replaceFragment(SixthFragment.fragment())
    }

    private fun replaceFragment(fragment: BaseFragment) {
        if (fragment is MainFragment) {
            supportFragmentManager.beginTransaction().replace(R.id.main, fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.main, fragment)
                .addToBackStack(null).commit()
        }
    }
}