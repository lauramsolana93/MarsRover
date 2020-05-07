package com.kotlin.seat.seatmars.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.seat.seatmars.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_splash
        )
        GlobalScope.launch {
            navigateToMarsPlateau()
        }

    }

    private suspend fun navigateToMarsPlateau() {
        delay(2000)
        var intent = Intent(this, MarsPlateauActivity::class.java)
        startActivity(intent)
    }
}
