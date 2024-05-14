package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class Daily_view_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_view)
        val back: ImageButton = findViewById(R.id.back)
        val fetch: Button = findViewById(R.id.fetch)
       back.setOnClickListener{
            val intent_discard = Intent(this, MainActivity::class.java)
            startActivity(intent_discard)

        }

        fetch.setOnClickListener{
            val intent_discard = Intent(this, Fetching_activity::class.java)
            startActivity(intent_discard)

        }
    }
}