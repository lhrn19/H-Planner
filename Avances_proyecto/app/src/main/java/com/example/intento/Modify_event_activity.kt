package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class Modify_event_activity : AppCompatActivity() {
    private var m_presionado = true
    private var l_presionado = true
    private var mie_presionado = true
    private var j_presionado = true
    private var v_presionado = true
    private var sa_presionado = true
    private var do_presionado = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_event)

        //botones pare seleccionar el día




        val discard_new_event: ImageButton = findViewById(R.id.back)
        discard_new_event.setOnClickListener{
            val intent_discard = Intent(this, Event_detail_activity::class.java)
            startActivity(intent_discard)

        }

        val lunes: ImageButton = findViewById(R.id.monday)
        val martes: ImageButton = findViewById(R.id.tuesday)
        val mierc: ImageButton = findViewById(R.id.wednesday)
        val jueves: ImageButton = findViewById(R.id.thursday)
        val vier: ImageButton = findViewById(R.id.friday)
        val sab: ImageButton = findViewById(R.id.saturday)
        val domin: ImageButton = findViewById(R.id.sunday)


        lunes.setOnClickListener {
            if (l_presionado) {
                lunes.setImageResource(R.drawable.m_l)
                l_presionado = false
            } else {

                lunes.setImageResource(R.drawable.m)
                l_presionado = true
            }

            martes.setOnClickListener {
                if(m_presionado) {
                    martes.setImageResource(R.drawable.t_l)
                    m_presionado = false
                } else {
                    martes.setImageResource(R.drawable.t)
                    m_presionado = true
                }
            }

            mierc.setOnClickListener {
                if (mie_presionado) {
                    mierc.setImageResource(R.drawable.w_p)
                    mie_presionado = false
                } else {
                    mierc.setImageResource(R.drawable.w)
                    mie_presionado = true
                }
            }

            jueves.setOnClickListener {
                if (j_presionado) {
                    jueves.setImageResource(R.drawable.t_l)
                    j_presionado = false
                } else {
                    jueves.setImageResource(R.drawable.t)
                    j_presionado = true
                }
            }

            vier.setOnClickListener {
                if (v_presionado) {
                    vier.setImageResource(R.drawable.f_l)
                    v_presionado = false
                } else {
                    vier.setImageResource(R.drawable.f)
                    v_presionado = true
                }
            }

            sab.setOnClickListener {
                if (sa_presionado) {
                    sab.setImageResource(R.drawable.s_l)
                    sa_presionado = false
                } else {
                    sab.setImageResource(R.drawable.s)
                    sa_presionado = true
                }
            }

            domin.setOnClickListener {
                if (do_presionado) {
                    domin.setImageResource(R.drawable.s_l)
                    do_presionado = false
                } else {
                    domin.setImageResource(R.drawable.s)
                    do_presionado = true
                }
            }


        }
    }
}