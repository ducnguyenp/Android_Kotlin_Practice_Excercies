package com.example.practiceallexercise.activities.sqlLite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practiceallexercise.R
import com.example.practiceallexercise.models.Student

class StudentAdapter(var context: Context, var studentList: ArrayList<Student>) :
    RecyclerView.Adapter<ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.sql_lite_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = studentList[position]
        if (holder is ViewHolder) {
            holder.itemView.findViewById<TextView>(R.id.tv_student_name).text = student.name
            holder.itemView.findViewById<ImageView>(R.id.btn_delete_student).setOnClickListener {
                onClickListener?.onDelete(position, student)
            }
            holder.itemView.findViewById<ImageView>(R.id.btn_update_student).setOnClickListener {
                holder.itemView?.findViewById<EditText>(R.id.et_update_student_name)?.visibility =
                    View.VISIBLE
                holder.itemView?.findViewById<TextView>(R.id.tv_student_name)?.visibility =
                    View.GONE
                holder.itemView?.findViewById<ImageView>(R.id.btn_update_student)?.visibility =
                    View.GONE
                holder.itemView?.findViewById<ImageView>(R.id.btn_delete_student)?.visibility =
                    View.GONE
                holder.itemView?.findViewById<ImageView>(R.id.btn_confirm)?.visibility =
                    View.VISIBLE
                holder.itemView?.findViewById<EditText>(R.id.et_update_student_name)
                    ?.setText(student.name)
                holder.itemView?.findViewById<ImageView>(R.id.btn_confirm)?.setOnClickListener {
                    onClickListener?.onUpdate(position, student)
                }
            }
        }
    }

    interface OnClickListener {
        fun onDelete(position: Int, student: Student) {}
        fun onUpdate(position: Int, student: Student) {}
    }

    fun setOnClickLisener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    private class MyViewHolder(view: View) : ViewHolder(view)
}