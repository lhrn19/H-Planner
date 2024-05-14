package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Track_habit_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.track_habits)

        val go_new_habit: Button = findViewById(R.id.your_go_daily)
        go_new_habit.setOnClickListener{
            val intent_new_habit = Intent(this, Daily_view_activity::class.java)
            startActivity(intent_new_habit)

        }
    }
}