package com.example.practiceallexercise.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityCoroutineBinding
import com.example.practiceallexercise.utils.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CoroutineActivity : BaseActivity() {
    var binding: ActivityCoroutineBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.navigationBarCoroutine?.let {
            setUpNavigationBar(
                resources.getString(R.string.coroutine),
                it
            )
        }
        binding?.btnExecute?.setOnClickListener {
            lifecycleScope.launch {
                execute()
            }
        }
    }

    private suspend fun execute() {
        withContext(Dispatchers.IO) {
            for (i in 1..100000) {
                Log.i("Delay", "" + i)
            }
            runOnUiThread {
                Toast.makeText(this@CoroutineActivity, "This execute is done", Toast.LENGTH_LONG).show()
            }
        }
    }
}