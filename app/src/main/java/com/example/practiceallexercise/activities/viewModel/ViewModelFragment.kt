package com.example.practiceallexercise.activities.viewModel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.practiceallexercise.R
import kotlinx.android.synthetic.main.fragment_view_model.*

class ViewModelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_view_model, container, false)
        onSetupUI(view)
        return view
    }

    fun onSetupUI(view: View) {
        view.findViewById<Button>(R.id.btn_descrease)?.setOnClickListener {
            (activity as ViewModelActivity).viewModel?.decrementBadgeCount()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as ViewModelActivity).viewModel.badgeCount.observe(this) {
            tv_fragment_number.text = it.toString()
        }
    }
}