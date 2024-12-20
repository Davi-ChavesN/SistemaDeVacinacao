package com.controlevacinacao.extiv.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.tools.ToolsEncryption
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.UsuarioDAO

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
        val chamarTelaUsuario = Intent(applicationContext, TelaUsuario::class.java)
        val bundle = Bundle()

        val dataBase = Banco(applicationContext)
        val usuarioDAO = UsuarioDAO(dataBase)
        val toolsEncryption = ToolsEncryption()


        bt_login_initial_screen.setOnClickListener {
            var loginSuccess = false
            val listaUsuarios = usuarioDAO.select()
            Log.i("Teste", "${listaUsuarios}")

            for(usuario in listaUsuarios){
                var dadosUsuario = usuario.split(" - ").toTypedArray()
                //Log.i("Teste", "${dadosUsuario[4]} - ${toolsEncryption.encrypt(et_password_initial_screen.text.toString())}")

                if(dadosUsuario[3].toString().equals(et_user_initial_screen.text.toString()) &&
                    dadosUsuario[4].toString().equals(toolsEncryption.encrypt(et_password_initial_screen.text.toString()))){
                    bundle.apply {
                        putInt("userCode", dadosUsuario[0].toString().toInt())
                        putString("userName", dadosUsuario[1].toString())
                    }
                    chamarTelaUsuario.putExtra("user", bundle)
                    loginSuccess = true
                }
            }
            if(loginSuccess){
                Toast.makeText(this, "Login bem sucedido", Toast.LENGTH_SHORT).show()
                startActivity(chamarTelaUsuario)
            }else{
                Toast.makeText(this, "Usuário/senha incorreto", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
        }

        bt_register_initial_screen.setOnClickListener {
            startActivity(chamarTelaCadastro)
        }
    }

    fun limparCampos() {
        et_user_initial_screen.text.clear()
        et_password_initial_screen.text.clear()
    }
}