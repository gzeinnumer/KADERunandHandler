package com.gzeinnumer.kaderunandhandler

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postAtTime
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRandom = Random()
        mHandler = Handler()

        mRunnable = Runnable {
            // Do something here
            root_layout.setBackgroundColor(randomHSVColor())
            text_view.text = "Handler Example\n" +
                    "Random Number : ${mRandom.nextInt(100)}"

            //start handler
            //NORMAL
            mHandler.postDelayed(mRunnable, 1000)
            //KTX
            mHandler.postDelayed(delayInMillis = 1000) { mRunnable }
        }

        //NORMAL
        mHandler.postDelayed({
            text_view.text = "Handler Short Code\n" +
                    "Random Number : ${mRandom.nextInt(100)}"
            text_view.setTextColor(randomHSVColor())
            root_layout.setBackgroundColor(Color.WHITE)
        }, 3000) // 3 seconds delay task execution

        //KTX
        mHandler.postDelayed(delayInMillis = 3000) {
            text_view.text = "Handler Short Code\n" +
                    "Random Number : ${mRandom.nextInt(100)}"
            text_view.setTextColor(randomHSVColor())
            root_layout.setBackgroundColor(Color.WHITE)
        }

        //stop run
        mHandler.removeCallbacks(mRunnable)
    }

    fun randomHSVColor(): Int {
        val hue = mRandom.nextInt(361)
        val saturation = 1.0f
        val value = 1.0f
        val alpha = 255
        return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))
    }
}