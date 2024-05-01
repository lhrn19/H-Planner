package com.example.prueba2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.budget_main)}}
        /*
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        navigationView =findViewById(R.id.navigationView)


        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId){
                R.id.nav_account ->{
                    true
                }
                R.id.nav_logout ->{
                    true
                }
                else ->{false}
            }
            drawerLayout.close()
            true
        }
        /*
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar.setHomeAsUpIndicator(R.drawable.menu)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_account ->{

                    true
                }
                R.id.nav_settings ->{
                    true
                }
                R.id.nav_logout ->{
                    true
                }
                else ->{false}
            }*/

        }
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->{
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}*/
         */
