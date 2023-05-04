package com.example.practiceallexercise.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practiceallexercise.R
import com.example.practiceallexercise.activities.intent.IntentActivity
import com.example.practiceallexercise.activities.liveData.LiveDataActivity
import com.example.practiceallexercise.activities.sqlLite.SqlLiteActivity
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
        binding?.coroutineBtn?.setOnClickListener(this)
        binding?.requestCameraBtn?.setOnClickListener(this)
        binding?.sqlLiteBtn?.setOnClickListener(this)
        binding?.liveDataBtn?.setOnClickListener(this)
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
            R.id.coroutine_btn -> {
                var intent = Intent(this, CoroutineActivity::class.java)
                startActivity(intent)
            }
            R.id.request_camera_btn -> {
                var intent = Intent(this, RequestCameraActivity::class.java)
                startActivity(intent)
            }
            R.id.sql_lite_btn -> {
                var intent = Intent(this, SqlLiteActivity::class.java)
                startActivity(intent)
            }
            R.id.live_data_btn -> {
                var intent = Intent(this, LiveDataActivity::class.java)
                startActivity(intent)
            }
        }
    }
}