package com.example.practiceallexercise.utils

import com.example.practiceallexercise.models.Student

object Constants {
    const val INTENT_RECEIVER = "INTENT_RECEIVER"
    const val READ_STORAGE_PERMISSION_CODE = 1
    const val PICK_IMAGE_REQUEST_CODE: Int = 2
    const val CAMERA_PERMISSION_CODE: Int = 3
    const val CAMERA_REQUEST_CODE: Int = 3
    const val INTENT_RECEIVER_SEND_MESSAGE_BACK = 0
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val StudentList: ArrayList<Student> =
        arrayListOf(
            Student(1, "Duc"),
            Student(2, "Nguyen"),
            Student(3, "Phuoc")
        )
}