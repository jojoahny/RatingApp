package com.example.ratingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var englishBtn:Button
    lateinit var arabicBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        englishBtn=findViewById(R.id.English)
        arabicBtn=findViewById(R.id.Arabic)
        englishBtn.setOnClickListener {
            val intent=Intent(this,MainActivity2::class.java)
            localHelper().setLocale(this,"en")
            startActivity(intent)
            finish()
        }
        arabicBtn.setOnClickListener {
            localHelper().setLocale(this,"ar")
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}