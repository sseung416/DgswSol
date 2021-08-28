package com.example.a2ndproject.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.a2ndproject.R
import com.example.a2ndproject.view.adapter.MainViewPagerAdapter
import com.example.a2ndproject.view.fragment.LifeFragment
import com.example.a2ndproject.view.fragment.MainFragment
import com.example.a2ndproject.view.fragment.MyAssetsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewPager2)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        initViewPager()
        initBottomNavigationView()
    }

    private fun initViewPager() {
        val list = listOf(MyAssetsFragment(), MainFragment(), LifeFragment())

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