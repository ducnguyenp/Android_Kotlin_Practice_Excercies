package com.example.practiceallexercise.utils

import com.example.practiceallexercise.models.Student

object Constants {
    const val INTENT_RECEIVER = "INTENT_RECEIVER"
    const val INTENT_RECEIVER_SEND_MESSAGE_BACK = 0
    val StudentList: ArrayList<Student> =
        arrayListOf(
            Student(1, "Duc"),
            Student(2, "Nguyen"),
            Student(3, "Phuoc")
        )
}