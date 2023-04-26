package com.example.practiceallexercise.activities.intent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityIntentReceiveBinding
import com.example.practiceallexercise.models.Student
import com.example.practiceallexercise.utils.Constants

class IntentReceiveActivity : AppCompatActivity() {
    var binding: ActivityIntentReceiveBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentReceiveBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpNavigation()
        receiveIntent()
        onBackToIntent()
    }

    private fun setUpNavigation() {
        setSupportActionBar(binding?.navigationBarIntentReceive)
        supportActionBar?.title = resources.getString(R.string.intent_receive)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.navigationBarIntentReceive?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun receiveIntent() {
        if (intent.hasExtra(Constants.INTENT_RECEIVER)) {
            var student: Student = intent.getParcelableExtra(Constants.INTENT_RECEIVER)!!
            binding?.tvIntentReceiveMessage?.text = student.name!!
        }
    }

    private fun onBackToIntent() {
        binding?.btnBackToIntent?.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}