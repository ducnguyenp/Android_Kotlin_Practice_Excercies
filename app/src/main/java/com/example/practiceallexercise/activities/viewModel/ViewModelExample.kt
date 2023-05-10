package com.example.practiceallexercise.activities.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelExample: ViewModel() {
    private val mBadgeCount = MutableLiveData<Int>()
    var number = 0

    val badgeCount: MutableLiveData<Int>
        get() = mBadgeCount

    fun incrementBadgeCount() {
        mBadgeCount.value = ++number
    }

    fun decrementBadgeCount() {
        mBadgeCount.value = --number
    }
}