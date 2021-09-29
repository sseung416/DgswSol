package com.example.a2ndproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.a2ndproject.R
import com.example.a2ndproject.ui.adapter.MainViewPagerAdapter
import com.example.a2ndproject.ui.fragment.LifeFragment
import com.example.a2ndproject.ui.fragment.HomeFragment
import com.example.a2ndproject.ui.fragment.MyAssetsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val viewPager2: ViewPager2 by lazy { findViewById(R.id.viewPager2) }
    private val bottomNavigationView: BottomNavigationView by lazy { findViewById(R.id.bottomNavigationView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
        initBottomNavigationView()

        viewPager2.currentItem = 1
    }

    private fun initViewPager() {
        val list = listOf(MyAssetsFragment(), HomeFragment(), LifeFragment())

        viewPager2.adapter = MainViewPagerAdapter(this, list)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun initBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.action_my_assets_bottom_navigation -> {
                    viewPager2.currentItem = 0
                    true
                }
                R.id.action_banking_bottom_navigation -> {
                    viewPager2.currentItem = 1
                    true
                }
                R.id.action_life_bottom_navigation -> {
                    viewPager2.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }

}