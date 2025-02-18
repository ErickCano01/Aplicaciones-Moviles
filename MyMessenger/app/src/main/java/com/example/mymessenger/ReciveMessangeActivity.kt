package com.example.mymessenger

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReciveMessangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recive_messange)

        val messageTextView: TextView = findViewById(R.id.message)
        val message = intent.getStringExtra("message")
        messageTextView.text = message
    }
}