package com.example.practiceallexercise.activities.liveData

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.practiceallexercise.R
import kotlinx.android.synthetic.main.fragment_live_data.*

class LiveDataFragment : Fragment() {
    private val changeObserver = Observer<String> { value ->
        value?.let {
            text_fragment?.text = it
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_live_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LiveDataActivity).liveData.observe(this, changeObserver)
    }

}