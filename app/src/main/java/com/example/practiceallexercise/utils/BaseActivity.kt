package com.example.practiceallexercise.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.practiceallexercise.R

open class BaseActivity: AppCompatActivity() {
    fun setUpNavigationBar(title: String, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        supportActionBar?.title = title
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}