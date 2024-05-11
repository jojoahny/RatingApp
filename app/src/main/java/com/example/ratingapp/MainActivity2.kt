package com.example.ratingapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.Locale

class MainActivity2 : AppCompatActivity() {
    lateinit var paragraph: TextView
    lateinit var rateUs: Button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        paragraph = findViewById(R.id.paragraph)
        rateUs = findViewById(R.id.rateUs)
        val channel = NotificationChannel(
            "my_channel_id",
            "My Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 20, intent, PendingIntent.FLAG_IMMUTABLE)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }else{
        rateUs.setOnClickListener {
            val viewXML = layoutInflater.inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(viewXML)
            val dialog = builder.create()
            dialog.setCancelable(false)
            val RatingBar: RatingBar = viewXML.findViewById(R.id.ratingBar)
            val Btn: Button = viewXML.findViewById(R.id.Btn)
            Btn.setOnClickListener {
                dialog.dismiss()
                val sendNottification = NotificationCompat.Builder(this, "my_channel_id")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(resources.getString(R.string.rating))
                    .setContentText(RatingBar.rating.toString())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                NotificationManagerCompat.from(this).notify(1, sendNottification.build())
            }
            dialog.show()
        }}
    }

}