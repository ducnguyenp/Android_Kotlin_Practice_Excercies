package com.example.practiceallexercise.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceallexercise.R
import com.example.practiceallexercise.adapters.RecyclerViewAdapter
import com.example.practiceallexercise.databinding.ActivityRecyclerViewBinding
import com.example.practiceallexercise.models.Student
import com.example.practiceallexercise.utils.Constants

class RecyclerViewActivity : AppCompatActivity() {
    var binding: ActivityRecyclerViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpNavigationBar()
        setRecyclerView()
    }

    private fun setUpNavigationBar() {
        setSupportActionBar(binding?.navigationBarRecyclerView)
        supportActionBar?.title = resources.getString(R.string.recycler_view)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.navigationBarRecyclerView?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecyclerView() {
        binding?.recyclerViewList?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerViewList?.setHasFixedSize(true)
        var adapter = RecyclerViewAdapter(this, Constants.StudentList)
        binding?.recyclerViewList?.adapter = adapter

        adapter?.setOnClickListener(object: RecyclerViewAdapter.OnClickListener {
            override fun onClick(position: Int, student: Student) {
                Toast.makeText(this@RecyclerViewActivity, "This is student ${student.name} !!!", Toast.LENGTH_LONG).show()
            }
        })
    }
}