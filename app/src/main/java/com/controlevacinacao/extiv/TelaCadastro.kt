package com.controlevacinacao.extiv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaCadastro : AppCompatActivity() {

    lateinit var bt_return_register_screen: FloatingActionButton
    lateinit var et_name_register_screen: EditText
    lateinit var et_email_register_screen: EditText
    lateinit var et_user_register_screen: EditText
    lateinit var et_password_register_screen: EditText
    lateinit var bt_register_register_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)


        bt_return_register_screen = findViewById(R.id.bt_return_register_screen)
        et_name_register_screen = findViewById(R.id.et_name_register_screen)
        et_email_register_screen = findViewById(R.id.et_email_register_screen)
        et_user_register_screen = findViewById(R.id.et_user_register_screen)
        et_password_register_screen = findViewById(R.id.et_password_register_screen)
        bt_register_register_screen = findViewById(R.id.bt_register_register_screen)

        val dataBase = Banco(applicationContext)
        val usuarioDAO = UsuarioDAO(dataBase)


        bt_register_register_screen.setOnClickListener {
            if(et_name_register_screen.text.isBlank() || et_email_register_screen.text.isBlank() ||
                et_user_register_screen.text.isBlank() || et_password_register_screen.text.isBlank()){
                Toast.makeText(this, "Prencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                val usuario = Usuario(et_name_register_screen.text.toString(), et_email_register_screen.text.toString(),
                    et_user_register_screen.text.toString(), et_password_register_screen.text.hashCode().toString())
                usuarioDAO.insert(usuario)
                Toast.makeText(this, "Usuário cadastrado", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
        }

        bt_return_register_screen.setOnClickListener {
            finish()
        }

    }

    fun limparCampos() {
        et_name_register_screen.text.clear()
        et_email_register_screen.text.clear()
        et_user_register_screen.text.clear()
        et_password_register_screen.text.clear()
    }
}