package com.grumpyshoe.circleprogressbar.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.grumpyshoe.circularprogressbar.R
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.circleProgressBar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val rnd = Random(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set max value to '20'
//        circleProgressBar.setMax(20)

        // add click listener to button
        button.setOnClickListener {

            // generate new value between 0 - 20 and update the progress view
            val value = rnd.nextInt(0, 20)
            circleProgressBar.updateProgress(value)
            Log.d(javaClass.simpleName, "Next Value: $value")
        }
    }
}