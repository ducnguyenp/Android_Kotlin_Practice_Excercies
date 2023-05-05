package com.example.practiceallexercise.activities.retrofit

import android.os.Bundle
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityRetrofitBinding
import com.example.practiceallexercise.utils.BaseActivity

class RetrofitActivity : BaseActivity() {
    var binding: ActivityRetrofitBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.navigationRetrofit?.let {
            setUpNavigationBar(
                resources.getString(R.string.retrofit),
                it
            )
        }
        setUpUi()
    }

    private fun setUpUi(){
        binding?.btnFetchApi?.setOnClickListener {
            RetrofitApi(this@RetrofitActivity).getUserDetail()
        }
    }
}