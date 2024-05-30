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
            val intent_discard = Intent(this, Event_main_activity::class.java)
            startActivity(intent_discard)

        }
        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}

        fetch.setOnClickListener{
            val intent_discard = Intent(this, Your_calendars_activity::class.java)
            startActivity(intent_discard)

        }
    }
}