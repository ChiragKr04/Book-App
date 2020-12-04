package com.internshala.bookhub.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.internshala.bookhub.R


class SplashScreenActivity : AppCompatActivity() {

    lateinit var splashcard:CardView
    lateinit var splashimglayout:RelativeLayout
    lateinit var splashimg:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashcard = findViewById(R.id.splashcard)
        splashimg = findViewById(R.id.imgSplash)
        splashimglayout = findViewById(R.id.splashimglayout)

        if (supportActionBar != null)
            supportActionBar?.hide()

        /*splashcard.rotationX = 180f
        Handler().postDelayed({
            splashcard.rotationY = 180f
        },1000)
        splashcard.rotationX = 180f*/


// first quarter turn
        /*splashcard.animate().withLayer()
            .rotationY(90f)
            .setDuration(1000)
            .withEndAction(
                Runnable {
                    splashcard.rotationY = 180f
                    // second quarter turn
                    splashcard.rotationY = -90f;
                    splashcard.animate().withLayer()
                        .rotationY(0f)
                        .setDuration(1000)
                        .start()
                }
            ).start()*/

        Handler().postDelayed({
            val startAct = Intent(this,
                MainActivity::class.java)
            startActivity(startAct)
        },4000)
    }
}