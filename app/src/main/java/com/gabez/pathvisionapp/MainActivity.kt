package com.gabez.pathvisionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.iammert.library.AnimatedTabLayout

const val NUM_PAGES = 2

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

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = ScreenSlidePageFragment()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) super.onBackPressed()
        else viewPager.currentItem = viewPager.currentItem - 1
    }


}