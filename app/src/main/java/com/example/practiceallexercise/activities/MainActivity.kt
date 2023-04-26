package com.example.practiceallexercise.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practiceallexercise.R
import com.example.practiceallexercise.activities.intent.IntentActivity
import com.example.practiceallexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.notificationBtn?.setOnClickListener(this)
        binding?.notificationFirebaseBtn?.setOnClickListener(this)
        binding?.recyclerViewBtn?.setOnClickListener(this)
        binding?.intentBtn?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.notification_btn -> {
                var intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
            R.id.notification_firebase_btn -> {
                var intent = Intent(this, NotificationFirebaseActivity::class.java)
                startActivity(intent)
            }
            R.id.recycler_view_btn -> {
                var intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }
            R.id.intent_btn -> {
                var intent = Intent(this, IntentActivity::class.java)
                startActivity(intent)
            }
        }
    }
}