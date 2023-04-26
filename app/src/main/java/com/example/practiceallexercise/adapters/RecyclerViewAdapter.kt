package com.example.practiceallexercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practiceallexercise.R
import com.example.practiceallexercise.models.Student

class RecyclerViewAdapter(var context: Context, var listStudent: ArrayList<Student> ):
    RecyclerView.Adapter<ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = listStudent[position]
        if (holder is ViewHolder) {
            holder.itemView.findViewById<TextView>(R.id.rv_student_id).text = student.id!!.toString()
            holder.itemView.findViewById<TextView>(R.id.rv_student_name).text = student.name!!

            holder.itemView.findViewById<LinearLayout>(R.id.rv_student_item).setOnClickListener{
                onClickListener?.onClick(position, student)
            }
        }
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    interface OnClickListener {
        fun onClick(position: Int, student: Student) { }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    private class MyViewHolder(view: View): ViewHolder(view)
}