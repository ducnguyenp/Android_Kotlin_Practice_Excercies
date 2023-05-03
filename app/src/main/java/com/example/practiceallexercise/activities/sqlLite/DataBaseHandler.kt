package com.example.practiceallexercise.activities.sqlLite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practiceallexercise.models.Student
import java.io.IOException

class DataBaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SchoolDatabase"
        private const val TABLE_NAME = "ClassRoomTable"
        private const val KEY_ID = "_id"
        private const val STUDENT_NAME = "studentName"
        private const val STUDENT_GENDER = "studentGender"
        private const val STUDENT_RANK = "studentRank"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createClass = ("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                STUDENT_NAME + " TEXT," +
                STUDENT_GENDER + " TEXT," +
                STUDENT_RANK + " TEXT)")
        db?.execSQL(createClass)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addStudent(student: Student): Long {
        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(STUDENT_NAME, student.name)
        contentValue.put(STUDENT_GENDER, "Male")
        contentValue.put(STUDENT_RANK, "Stupid")

        val result = db.insert(TABLE_NAME, null, contentValue)
        db.close()
        return result
    }

    @SuppressLint("Range")
    fun getStudentList(): ArrayList<Student> {
        var studentList: ArrayList<Student> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val student = Student(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString((cursor.getColumnIndex(STUDENT_NAME))))
                    studentList.add(student)
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: IOException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        return studentList
    }

    fun deleteStudent(studentId: Int): Int {
        var db = this.writableDatabase
        var success = db.delete(TABLE_NAME, "$KEY_ID=$studentId", null)
        db.close()
        return success
    }

    fun updateStudent(student: Student): Int {
        var db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(STUDENT_NAME, student.name)
        contentValues.put(STUDENT_GENDER, "Male")
        contentValues.put(STUDENT_RANK, "Stupid")

        var success = db.update(TABLE_NAME, contentValues, KEY_ID + "=" + student.id, null)
        db.close()
        return success
    }
}