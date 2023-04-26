package com.example.practiceallexercise.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityNotificationFirebaseBinding

class NotificationFirebaseActivity : AppCompatActivity() {
    var binding: ActivityNotificationFirebaseBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityNotificationFirebaseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpNavigationBar()
    }

    private fun setUpNavigationBar() {
        setSupportActionBar(binding?.navigationFirebaseInformation)
        supportActionBar?.title = resources.getString(R.string.firebase_notification)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        binding?.navigationFirebaseInformation?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}