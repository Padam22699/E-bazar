package com.example.e_bazar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging

class SlashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash_screen)
        this.supportActionBar?.hide()


        //for push notification firebase


        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(applicationContext , MainActivity::class.java)
            startActivity(intent)
            finish()
        } , 2000)
    }

}