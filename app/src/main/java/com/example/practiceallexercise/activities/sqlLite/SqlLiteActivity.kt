package com.example.practiceallexercise.activities.sqlLite

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivitySqlLiteBinding
import com.example.practiceallexercise.models.Student
import com.example.practiceallexercise.utils.BaseActivity

class SqlLiteActivity : BaseActivity() {
    var binding: ActivitySqlLiteBinding? = null
    var studentList: ArrayList<Student> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqlLiteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.navigationBarSqlLite?.let {
            setUpNavigationBar(
                resources.getString(R.string.sql_lite),
                it
            )
        }
        binding?.btnAddStudent?.setOnClickListener {
            onAddStudent()
        }
        setUpRecyclerView()
    }

    private fun onAddStudent() {
        val studentName = binding?.etStudentName?.text.toString()
        var dbHandler = DataBaseHandler(this)
        if (studentName.isNotEmpty()) {
            var student = Student(0, studentName!!)
            var addStudentResult = dbHandler.addStudent(student)
            if (addStudentResult > 0) {
                setUpRecyclerView()
                Toast.makeText(this, "Add student successfully!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Add student failed, please try again!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun deleteStudent(studentId: Int) {
        var dbHandler = DataBaseHandler(this@SqlLiteActivity)
        var result = dbHandler.deleteStudent(studentId)
        if (result > 0) {
            setUpRecyclerView()
            Toast.makeText(this@SqlLiteActivity, "Delete student successfully!", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(this@SqlLiteActivity, "Delete student successfully!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun updateStudent(student: Student) {
        var dbHandler = DataBaseHandler(this@SqlLiteActivity)
        var updateName = binding?.rvStudentList?.findViewById<EditText>(R.id.et_update_student_name)?.text.toString()
        var updateStudent = Student(student.id, updateName)
        var result = dbHandler.updateStudent(updateStudent)
        if (result > 0) {
            setUpRecyclerView()
            Toast.makeText(this, "Update student success", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Update student failed, please try again!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setUpRecyclerView() {
        var dbHandler = DataBaseHandler(this)
        var studentList: ArrayList<Student> = dbHandler.getStudentList()
        this.studentList = studentList
        var adapter = StudentAdapter(this, studentList)
        binding?.rvStudentList?.layoutManager = LinearLayoutManager(this)
        binding?.rvStudentList?.setHasFixedSize(true)
        binding?.rvStudentList?.adapter = adapter
        adapter?.setOnClickLisener(object : StudentAdapter.OnClickListener {
            override fun onDelete(position: Int, student: Student) {
                deleteStudent(student.id)
            }

            override fun onUpdate(position: Int, student: Student) {
                updateStudent(student)
            }
        })
    }
}