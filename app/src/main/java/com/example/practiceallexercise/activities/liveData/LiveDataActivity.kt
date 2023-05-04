package com.example.practiceallexercise.activities.liveData

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityLiveDataBinding
import com.example.practiceallexercise.utils.BaseActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LiveDataActivity : BaseActivity() {
    var binding: ActivityLiveDataBinding? = null
    val liveData = MutableLiveData<String>()

    private val changeObserver = Observer<String> { value ->
        value?.let {
            binding?.tvLiveDataText?.text = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.navigationBarLiveData)
        supportActionBar?.title = resources.getString(R.string.live_data)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.navigationBarLiveData?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpUI()
    }

    private fun setUpUI() {
        liveData.observe(this, changeObserver)

        binding?.btnChangeText?.setOnClickListener {
            binding?.tvLiveDataText?.resetLoader()
            MainScope().launch {
                delay(1000)
                liveData.value = "Text has been update"
            }
        }
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, LiveDataFragment())
                .addToBackStack("").commit()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}