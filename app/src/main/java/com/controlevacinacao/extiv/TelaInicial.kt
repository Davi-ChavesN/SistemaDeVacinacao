package com.controlevacinacao.extiv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TelaInicial : AppCompatActivity() {

    lateinit var et_user_initial_screen: EditText
    lateinit var et_password_initial_screen: EditText
    lateinit var bt_login_initial_screen: Button
    lateinit var bt_register_initial_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)


        et_user_initial_screen = findViewById(R.id.et_user_initial_screen)
        et_password_initial_screen = findViewById(R.id.et_password_initial_screen)
        bt_login_initial_screen = findViewById(R.id.bt_login_initial_screen)
        bt_register_initial_screen = findViewById(R.id.bt_register_initial_screen)

        val chamarTelaCadastro = Intent(applicationContext, TelaCadastro::class.java)


        bt_login_initial_screen.setOnClickListener {

        }

        bt_register_initial_screen.setOnClickListener {
            startActivity(chamarTelaCadastro)
        }

    }
}