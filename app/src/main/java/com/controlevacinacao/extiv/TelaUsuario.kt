
package com.controlevacinacao.extiv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.controlevacinacao.extiv.databinding.TelaUsuarioBinding

class TelaUsuario : AppCompatActivity() {

    private lateinit var bt_return_user_screen: FloatingActionButton
    private lateinit var iv_user_screen: ImageView
    private lateinit var tv_name_user_screen: TextView
    private lateinit var tv_uid_user_screen: TextView
    private lateinit var bt_add_pet_user_screen: Button
    private lateinit var lv_pets_user_screen: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_usuario)

        bt_return_user_screen = findViewById(R.id.bt_return_user_screen)
        iv_user_screen = findViewById(R.id.iv_user_screen)
        tv_name_user_screen = findViewById(R.id.tv_name_user_screen)
        tv_uid_user_screen = findViewById(R.id.tv_uid_user_screen)
        bt_add_pet_user_screen = findViewById(R.id.bt_add_pet_user_screen)
        lv_pets_user_screen = findViewById(R.id.lv_pets_user_screen)

        val chamarTelaEdicaoUsuario = Intent(applicationContext, TelaEdicaoUsuario::class.java)
        val chamarTelaCadastroPet = Intent(applicationContext, TelaCadastroPet::class.java)
        val bundle = intent.getBundleExtra("user")!!
        tv_name_user_screen.text = bundle.getString("userName", "").toString()
        tv_uid_user_screen.text = "UID: ${bundle.getInt("userCode", 0).toString()}"
        var userCode = bundle.getInt("userCode", 0).toString().toLong()

        val dataBase = Banco.getInstance(this)
        val usuarioDAO = UsuarioDAO(dataBase)
        val petDAO = PetDAO(dataBase)


        mostrarPets(petDAO, userCode)

        iv_user_screen.setOnClickListener {
            chamarTelaEdicaoUsuario.putExtra("user", bundle)
            startActivity(chamarTelaEdicaoUsuario)
        }

        bt_add_pet_user_screen.setOnClickListener {
            var pets = petDAO.select()
            Log.i("Teste", pets.toString())
            chamarTelaCadastroPet.putExtra("user", bundle)
            mostrarPets(petDAO, userCode)
            startActivity(chamarTelaCadastroPet)
        }

        bt_return_user_screen.setOnClickListener {
            finish()
        }
    }

    fun mostrarPets(petDAO: PetDAO, userCode: Long){
        var listaPets = petDAO.selectComWhere(userCode)
        var adaptador = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listaPets)
        lv_pets_user_screen.adapter = adaptador
    }
}