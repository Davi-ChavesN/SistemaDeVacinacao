package com.controlevacinacao.extiv.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.Usuario
import com.controlevacinacao.extiv.model.UsuarioDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaEdicaoUsuario : AppCompatActivity() {

    private lateinit var bt_return_user_edit_screen: FloatingActionButton
    private lateinit var et_name_user_edit_screen: EditText
    private lateinit var et_email_user_edit_screen: EditText
    private lateinit var bt_update_user_edit_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_edicao_usuario)

        bt_return_user_edit_screen = findViewById(R.id.bt_return_user_edit_screen)
        et_name_user_edit_screen = findViewById(R.id.et_name_user_edit_screen)
        et_email_user_edit_screen = findViewById(R.id.et_email_user_edit_screen)
        bt_update_user_edit_screen = findViewById(R.id.bt_update_user_edit_screen)

        val bundle = intent.getBundleExtra("user")!!
        var userCode = bundle.getInt("userCode", 0).toString().toLong()

        val dataBase = Banco.getInstance(this)
        val usuarioDAO = UsuarioDAO(dataBase)

        var usuarios = usuarioDAO.selectComWhere(userCode)
        var dadosSeparados = usuarios[0].split(" - ").toTypedArray()
        var usuario = Usuario(dadosSeparados[0].toInt(), dadosSeparados[1], dadosSeparados[2],
            dadosSeparados[3], dadosSeparados[4])

        et_name_user_edit_screen.setText(usuario.nome)
        et_email_user_edit_screen.setText(usuario.email)


        bt_update_user_edit_screen.setOnClickListener {
            try {
                // Captura os valores dos campos de texto
                usuario.nome = et_name_user_edit_screen.text?.toString().orEmpty()
                usuario.email = et_email_user_edit_screen.text?.toString().orEmpty()

                // Chama o método de atualização no DAO
                usuarioDAO.update(usuario)
                Toast.makeText(this, "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                Toast.makeText(this, "Erro ao atualizar usuário.", Toast.LENGTH_SHORT).show()
            }
        }

        bt_return_user_edit_screen.setOnClickListener {
            finish()
        }

    }
}