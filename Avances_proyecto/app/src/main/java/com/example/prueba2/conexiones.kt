package com.example.prueba2
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.LinearLayout
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignOutActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)

        auth = FirebaseAuth.getInstance()
        val buttonSignOut = findViewById<Button>(R.id.sign_out)
        buttonSignOut.setOnClickListener {
            auth.signOut()
            val intentSignOut = Intent(this, MainActivity::class.java)
            startActivity(intentSignOut)
            finish()
        }
    }
}

class Button_Change_Password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        val botontext_change_password = findViewById<Button>(R.id.change_password)
        botontext_change_password.setOnClickListener {
            val intent_btn_change_password = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent_btn_change_password)
        }
    }
}

class boton_update_prof_information : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val botontext_change_password = findViewById<ImageButton>(R.id.edit_profile_information)
        botontext_change_password.setOnClickListener {
            val intent_btn_update_prof_information = Intent(this, ModificarUserActivity::class.java)
            startActivity(intent_btn_update_prof_information)
        }
    }
}

//aqui quede en la revisión

class text_change_password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val text_change_password = findViewById<TextView>(R.id.change_password)
        text_change_password.setOnClickListener {
            val intent_change_password = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent_change_password)
        }
    }
}

class regresar_profile_info : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)
        val layout_regresar_profile_info = findViewById<ImageButton>(R.id.regresar_profile_info)
        layout_regresar_profile_info.setOnClickListener {
            val intent_regresar_pi = Intent(this, DashboardActivity::class.java)
            startActivity(intent_regresar_pi)
        }
    }
}

class regresar_change_password : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        val layout_regresar_change_password = findViewById<LinearLayout>(R.id.regresar_change_password)
        layout_regresar_change_password.setOnClickListener {
            val intent_regresar_cp = Intent(this, ConsultarUserActivity::class.java)
            startActivity(intent_regresar_cp)
        }
    }
}

class regresar_new_account : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_account)
        val layout_regresar_profile_info = findViewById<ImageButton>(R.id.regresar_new_account)
        layout_regresar_profile_info.setOnClickListener {
            val intent_regresar_na = Intent(this, DashboardActivity::class.java)
            startActivity(intent_regresar_na)
        }
    }
}


class update : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_information)
        val boton_sign_out = findViewById<Button>(R.id.update)
        boton_sign_out.setOnClickListener {
            val intent_update = Intent(this, ModificarUserActivity::class.java)
            startActivity(intent_update)
        }
    }
}

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val boton_sign_in = findViewById<Button>(R.id.sign_in)
        boton_sign_in.setOnClickListener{
            val intent_sign_in = Intent(this, DashboardActivity::class.java)
            startActivity(intent_sign_in)
        }
    }
}
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val buttonSignUp = findViewById<Button>(R.id.sign_up)
        buttonSignUp.setOnClickListener {
            val intentSignUp = Intent(this, NewUserActivity::class.java)
            startActivity(intentSignUp)
        }
    }
}