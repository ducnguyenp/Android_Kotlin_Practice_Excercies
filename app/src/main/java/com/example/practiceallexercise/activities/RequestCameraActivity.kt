package com.example.practiceallexercise.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityRequestCameraBinding
import com.example.practiceallexercise.utils.BaseActivity
import com.example.practiceallexercise.utils.Constants
import java.io.IOException


class RequestCameraActivity : BaseActivity() {
    var binding: ActivityRequestCameraBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestCameraBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.notificationRequestCamera?.let {
            setUpNavigationBar(
                resources.getString(R.string.request_camera),
                it
            )
        }
        onRequestImageGallery()
        onRequestCamera()
    }

    private fun onRequestCamera() {
        binding?.btnRequestCamera?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), Constants.CAMERA_PERMISSION_CODE)
            } else {
                val intent = Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
            }
        }
    }

    private fun onRequestImageGallery() {
        binding?.btnRequestGallery?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                var galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, Constants.PICK_IMAGE_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_STORAGE_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                var galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, Constants.PICK_IMAGE_REQUEST_CODE)
            } else {
                Toast.makeText(this, "You denied the permission for storage", Toast.LENGTH_LONG)
                    .show()
            }
        }
        if (requestCode == Constants.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(this, "You denied the permission for camera", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.PICK_IMAGE_REQUEST_CODE && data!!.data != null) {
            var mSelectedImageUri: Uri? =  data.data
            try {
                binding?.imageView?.setImageURI(mSelectedImageUri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (requestCode == Constants.CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val image: Bitmap = data.extras!!.get("data") as Bitmap
            try {
                binding?.imageView?.setImageBitmap(image)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}