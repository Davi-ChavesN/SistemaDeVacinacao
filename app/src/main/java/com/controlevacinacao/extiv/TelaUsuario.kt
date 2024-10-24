
package com.controlevacinacao.extiv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.controlevacinacao.extiv.databinding.TelaUsuarioBinding

class TelaUsuario : AppCompatActivity() {
    private lateinit var bt_return_user_screen: FloatingActionButton
    private lateinit var tv_name_user_screen: TextView
    private lateinit var tv_uid_user_screen: TextView
    private lateinit var bt_add_pet_user_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_usuario)

        bt_return_user_screen = findViewById(R.id.bt_return_user_screen)
        tv_name_user_screen = findViewById(R.id.tv_name_user_screen)
        tv_uid_user_screen = findViewById(R.id.tv_uid_user_screen)
        bt_add_pet_user_screen = findViewById(R.id.bt_add_pet_user_screen)

        val chamarTelaCadastroPet = Intent(applicationContext, TelaCadastroPet::class.java)
        val bundle = intent.getBundleExtra("user")!!
        tv_name_user_screen.text = bundle.getString("userName", "").toString()
        tv_uid_user_screen.text = "UID: ${bundle.getInt("userCode", 0).toString()}"
        var userCode = bundle.getInt("userCode", 0).toString().toLong()

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