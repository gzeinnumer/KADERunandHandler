package com.gzeinnumer.kaderunandhandler

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize a new Random instance
        mRandom = Random()

        // Initialize the handler instance
        mHandler = Handler()

        // Set a click listener for run button
        button_run.setOnClickListener{
            mRunnable = Runnable {
                // Do something here
                root_layout.setBackgroundColor(randomHSVColor())
                text_view.text = "Handler Example\n" +
                        "Random Number : ${mRandom.nextInt(100)}"

                // Schedule the task to repeat after 1 second
                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
            }

            // Schedule the task to repeat after 1 second
            mHandler.postDelayed(
                mRunnable, // Runnable
                1000 // Delay in milliseconds
            )
        }



        // Set a click listener for stop button
        button_stop.setOnClickListener{
            // Stop the periodic task
            mHandler.removeCallbacks(mRunnable)

            // Change the text view text
            text_view.text = "Handler call backs removed."
        }



        // Set a click listener for handler short code form button
        button_short.setOnClickListener{
            mHandler.postDelayed({
                // Change text view text
                text_view.text = "Handler Short Code\n" +
                        "Random Number : ${mRandom.nextInt(100)}"

                // Change the text view text color
                text_view.setTextColor(randomHSVColor())

                // Change layout background color
                root_layout.setBackgroundColor(Color.WHITE)
            }, 3000) // 3 seconds delay task execution

        }
    }



    // Custom method to generate random HSV color
    fun randomHSVColor(): Int {
        // Generate a random hue value between 0 to 360
        val hue = mRandom.nextInt(361)
        // We make the color depth full
        val saturation = 1.0f
        // We make a full bright color
        val value = 1.0f
        // We avoid color transparency
        val alpha = 255
        // Finally, generate the color
        // Return the color
        return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))
    }
}