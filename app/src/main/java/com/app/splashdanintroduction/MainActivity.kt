package com.app.splashdanintroduction

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.app.splashdanintroduction.adapter.SliderAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var btnNext: TextView
    private lateinit var btnFinish: TextView
    private lateinit var numberLayout:IntArray
    private var mContext = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.tblNext)
        btnFinish = findViewById(R.id.tblFinish)
        btnFinish.visibility = View.GONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.WHITE
        }

        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.dotIndicator)
        numberLayout =
            intArrayOf(R.layout.layout1, R.layout.layout2, R.layout.layout3, R.layout.layout1)
        val sliderAdapter = SliderAdapter(mContext, numberLayout)
        viewPager.adapter = sliderAdapter
        wormDotsIndicator.setViewPager(viewPager)


        btnNext.setOnClickListener {
            val current = getItem(+1)
            if (current < numberLayout.size) {
                viewPager.currentItem = current
            } else {
                btnNext.visibility = View.GONE
                btnFinish.visibility = View.VISIBLE
            }
        }

        btnFinish.setOnClickListener {
            startActivity(Intent(mContext, HomeActivity::class.java))
        }



        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(position == numberLayout.size -1){
                    btnNext.visibility = View.GONE
                    btnFinish.visibility = View.VISIBLE
                }else{
                    btnNext.visibility = View.VISIBLE
                    btnFinish.visibility = View.GONE
                }
            }

        })

    }
    fun getItem(i: Int):Int {
        return viewPager.currentItem + i
    }
}
