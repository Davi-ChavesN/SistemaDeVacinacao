package com.controlevacinacao.extiv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaUsuario : AppCompatActivity() {

    lateinit var bt_return_user_screen: FloatingActionButton
    lateinit var tv_name_user_screen: TextView
    lateinit var tv_uid_user_screen: TextView
    lateinit var bt_add_pet_user_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_usuario)

        bt_return_user_screen = findViewById(R.id.bt_return_user_screen)
        tv_name_user_screen = findViewById(R.id.tv_name_user_screen)
        tv_uid_user_screen = findViewById(R.id.tv_uid_user_screen)
        bt_add_pet_user_screen = findViewById(R.id.bt_add_pet_user_screen)

        val chamarTelaCadastroPet = Intent(applicationContext, TelaCadastroPet::class.java)
        val bundle = intent.getBundleExtra("user")!!
        tv_name_user_screen.setText(bundle.getString("userName", "").toString())
        tv_uid_user_screen.setText("UID: ${bundle.getInt(" userCode ", 0).toString()}")

        val dataBase = Banco(applicationContext)
        val usuarioDAO = UsuarioDAO(dataBase)
        val petDAO = PetDAO(dataBase)


        bt_add_pet_user_screen.setOnClickListener {
            chamarTelaCadastroPet.putExtra("user", bundle)
            startActivity(chamarTelaCadastroPet)
        }

        bt_return_user_screen.setOnClickListener {
            finish()
        }
    }
}