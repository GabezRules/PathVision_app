package com.gabez.pathvisionapp

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gabez.pathvisionapp.app.paths.MainFragment
import com.gabez.pathvisionapp.app.search.SearchFragment
import com.gabez.pathvisionapp.app.settings.SettingsFragment
import com.iammert.library.AnimatedTabLayout

const val NUM_PAGES = 3

class MainActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNav: AnimatedTabLayout

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
        viewPager.setPageTransformer(true, DepthPageTransformer())

        //setTheme(android.R.style.Theme_Material_NoActionBar)

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {

        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment{
            return when (position) {
                0 -> return@getItem MainFragment.newInstance()
                1 -> return@getItem SearchFragment.newInstance()
                2 -> return@getItem SettingsFragment.newInstance()
                else -> return@getItem MainFragment.newInstance()
            }
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) super.onBackPressed()
        else viewPager.currentItem = viewPager.currentItem - 1
    }


}