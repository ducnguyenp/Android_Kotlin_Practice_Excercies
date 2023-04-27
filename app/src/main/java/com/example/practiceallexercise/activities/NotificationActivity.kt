package com.example.practiceallexercise.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.practiceallexercise.R
import com.example.practiceallexercise.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    var binding: ActivityNotificationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpNavigationBar()

        createNotificationChange()
        binding?.testNotificationBtn?.setOnClickListener {
            sendNotification()
        }
    }

    private fun setUpNavigationBar() {
        var navigation: androidx.appcompat.widget.Toolbar? = binding?.navigationInformation
        setSupportActionBar(binding?.navigationInformation)
        supportActionBar?.title = resources.getString(R.string.notification)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_icon)
        binding?.navigationInformation?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun createNotificationChange() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val title = "Title notification"
            var description = "Description notification"
            val important = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, title, important).apply {
                description = description
            }
            var notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        var intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.icon_facebook)


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_small_notification)
            .setContentTitle("Example Notification")
            .setContentText("Example Notification detail")
            .setLargeIcon(bitmap)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_id_1"
    }
}