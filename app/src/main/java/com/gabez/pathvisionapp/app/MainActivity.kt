package com.gabez.pathvisionapp.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gabez.pathvisionapp.DepthPageTransformer
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.view.MainFragment
import com.gabez.pathvisionapp.app.search.view.SearchFragment
import com.gabez.pathvisionapp.app.settings.settingsWithAuth.SettingsAuthFragment
import com.gabez.pathvisionapp.app.settings.settingsWithoutAuth.SettingsFragment
import com.iammert.library.AnimatedTabLayout
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : FragmentActivity(), KoinComponent {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNav: AnimatedTabLayout

    private val viewModel: MainActivityViewModel by inject()

    val NUM_PAGES = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        bottomNav = findViewById(R.id.bottomNav)

        bottomNav.setupViewPager(viewPager)
        bottomNav.setTabChangeListener(object : AnimatedTabLayout.OnChangeListener {
            override fun onChanged(position: Int) {
            }
        })

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(true,
            DepthPageTransformer()
        )

        //setTheme(android.R.style.Theme_Material_NoActionBar)

        viewModel.currentUser.observeForever {userObj ->
            if(userObj != null) backToMainFragment()
        }

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {

        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment{
            return when (position) {
                0 -> MainFragment.newInstance()
                1 -> SearchFragment.newInstance()
                2 -> {
                    if(viewModel.currentUser.value == null) SettingsFragment.newInstance()
                    else SettingsAuthFragment.newInstance()
                }
                else -> MainFragment.newInstance()
            }
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) super.onBackPressed()
        else viewPager.currentItem = viewPager.currentItem - 1
    }

    fun backToMainFragment(){
        viewPager.currentItem = 0
    }


}