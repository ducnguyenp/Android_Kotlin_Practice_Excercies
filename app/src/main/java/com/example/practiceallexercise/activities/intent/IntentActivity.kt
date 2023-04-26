package com.example.practiceallexercise.activities.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityIntentBinding
import com.example.practiceallexercise.models.Student
import com.example.practiceallexercise.utils.Constants

class IntentActivity : AppCompatActivity() {
    var binding: ActivityIntentBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpNavigationBar()
        onGoToIntentReceive()
    }

    private fun setUpNavigationBar() {
        setSupportActionBar(binding?.navigationBarIntent)
        supportActionBar?.title = resources.getString(R.string.intent)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        binding?.navigationBarIntent?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun onGoToIntentReceive() {
        binding?.btnGoToIntentReceiver?.setOnClickListener {
            var student = Student(1, "Nguyen Phuoc Duc xin cheo")
            var intent = Intent(this, IntentReceiveActivity::class.java)
            intent.putExtra(Constants.INTENT_RECEIVER, student)
            startActivityForResult(intent, Constants.INTENT_RECEIVER_SEND_MESSAGE_BACK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.INTENT_RECEIVER_SEND_MESSAGE_BACK && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Send from intent receiver", Toast.LENGTH_LONG).show()
        }
    }
}