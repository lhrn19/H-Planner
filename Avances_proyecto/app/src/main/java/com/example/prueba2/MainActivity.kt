package com.example.prueba2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //create profile,change password,user_info
        setContentView(R.layout.login)
        val database = Firebase.database
        val myRef = database.getReference("Usuario_HiPlanner")

    }
    fun create_account(view:View){
        setContentView(R.layout.login)
    }
    fun sign_out(view:View){
        setContentView(R.layout.login)
    }
    fun sign_in(view:View){
        setContentView(R.layout.dashboard)
    }
    fun change_password(view:View){
        setContentView(R.layout.login)
    }
    fun update(view: View){
        setContentView(R.layout.profile_information)
    }

}

