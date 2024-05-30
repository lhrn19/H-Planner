package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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
        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}
    }
}