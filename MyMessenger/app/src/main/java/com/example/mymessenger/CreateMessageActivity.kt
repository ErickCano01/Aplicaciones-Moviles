package com.example.mymessenger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)

        val sendButton: Button = findViewById(R.id.send)
        val messageEditText: EditText = findViewById(R.id.message)

        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()
            val intent = Intent(this, ReciveMessangeActivity::class.java)
            intent.putExtra("message", message)
            startActivity(intent)
        }
    }
}