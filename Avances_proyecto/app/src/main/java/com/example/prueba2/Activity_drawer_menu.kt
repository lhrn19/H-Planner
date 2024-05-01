package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView



class Activity_drawer_menu : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView =findViewById(R.id.navigationView)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }



        navigationView.setNavigationItemSelectedListener {
                menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.nav_calendar ->{
                    setContentView(R.layout.dashboard)
                    true
                }
                R.id.nav_tracking ->{
                    setContentView(R.layout.dashboard)
                    true
                }
                R.id.nav_account ->{

                    true
                }
                R.id.nav_budget->{
                    val go_to_modify_close: ImageButton = findViewById(R.id.nav_budget)
                    go_to_modify_close.setOnClickListener{
                        /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
                        val intent_mod_bud = Intent(this, Budget_main_activity::class.java)
                        startActivity(intent_mod_bud)}
                    setContentView(R.layout.budget_main)
                    true
                }
                else ->{false}
            }
            drawerLayout.close()
            true
        }
    }
}