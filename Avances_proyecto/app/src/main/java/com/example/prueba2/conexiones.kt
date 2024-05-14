package com.example.prueba2
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView
import com.example.prueba2.R

class create_account : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_account)
        val boton_crear_cuenta = findViewById<Button>(R.id.create_account)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.login)
        }
    }
}

class sign_out : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val boton_sign_out = findViewById<Button>(R.id.sign_out)
        boton_sign_out.setOnClickListener {
            setContentView(R.layout.login)
        }
    }
}

class sign_in : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val boton_sign_in = findViewById<Button>(R.id.sign_in)
        boton_sign_in.setOnClickListener {
            setContentView(R.layout.dashboard)
        }
    }
}

class boton_change_password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        val boton_sing_in = findViewById<Button>(R.id.change_password)
        boton_sing_in.setOnClickListener {
            setContentView(R.layout.dashboard)
        }
    }
}

class sign_up : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val text_sign_up = findViewById<TextView>(R.id.sign_up)
        text_sign_up.setOnClickListener {
            setContentView(R.layout.new_account)
        }
    }
}

class text_change_password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val text_change_password = findViewById<TextView>(R.id.change_password)
        text_change_password.setOnClickListener {
            setContentView(R.layout.change_password)
        }
    }
}

class regresar_profile_info : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val layout_regresar_profile_info = findViewById<LinearLayout>(R.id.regresar_profile_info)
        layout_regresar_profile_info.setOnClickListener {
            setContentView(R.layout.dashboard)
        }
    }
}

class regresar_change_password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        val layout_regresar_change_password = findViewById<LinearLayout>(R.id.regresar_change_password)
        layout_regresar_change_password.setOnClickListener {
            setContentView(R.layout.profile_information)
        }
    }
}

class edit_profile_information : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val layout_regresar_change_password = findViewById<LinearLayout>(R.id.edit_profile_information)
        layout_regresar_change_password.setOnClickListener {
            setContentView(R.layout.edit_profile_information)
        }
    }
}
class update : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_information)
        val boton_sign_out = findViewById<Button>(R.id.update)
        boton_sign_out.setOnClickListener {
            setContentView(R.layout.profile_information)
        }
    }
}