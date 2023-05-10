package com.example.practiceallexercise.activities.viewModel

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityViewModelBinding
import com.example.practiceallexercise.utils.BaseActivity

class ViewModelActivity : BaseActivity() {
    var binding: ActivityViewModelBinding? = null
    lateinit var viewModel: ViewModelExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.navigationBarViewModel?.let {
            setUpNavigationBar(
                resources.getString(R.string.view_model),
                it
            )
        }

        viewModel = ViewModelProvider(this)[ViewModelExample::class.java]
        observeViewModel()
        initListeners()
        setUpFragment()
    }

    private fun initListeners() {
        binding?.btnIncrease?.setOnClickListener {
            viewModel.incrementBadgeCount()
        }
    }

    private fun observeViewModel() {
        viewModel.badgeCount.observe(this) {
            binding?.tvNumberCount?.text = it.toString()
        }
    }

    private fun setUpFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding?.viewModelFragment?.id!!, ViewModelFragment()).commit()
    }
}