
package com.controlevacinacao.extiv.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.Pet
import com.controlevacinacao.extiv.model.PetAdapter
import com.controlevacinacao.extiv.model.PetDAO
import com.controlevacinacao.extiv.model.UsuarioDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaUsuario : AppCompatActivity() {

    private lateinit var bt_return_user_screen: FloatingActionButton
    private lateinit var iv_user_screen: ImageView
    private lateinit var tv_name_user_screen: TextView
    private lateinit var tv_uid_user_screen: TextView
    private lateinit var bt_add_pet_user_screen: Button

    private lateinit var rvPetUserScreen: RecyclerView
    private lateinit var petList: ArrayList<Pet>
    private lateinit var petAdapter: PetAdapter

    private var userCode: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_usuario)

        bt_return_user_screen = findViewById(R.id.bt_return_user_screen)
        iv_user_screen = findViewById(R.id.iv_user_screen)
        tv_name_user_screen = findViewById(R.id.tv_name_user_screen)
        tv_uid_user_screen = findViewById(R.id.tv_uid_user_screen)
        bt_add_pet_user_screen = findViewById(R.id.bt_add_pet_user_screen)

        rvPetUserScreen = findViewById(R.id.rvPetUserScreen)
        rvPetUserScreen.setHasFixedSize(true)
        rvPetUserScreen.layoutManager = LinearLayoutManager(this)

        val chamarTelaEdicaoUsuario = Intent(applicationContext, TelaEdicaoUsuario::class.java)
        val chamarTelaCadastroPet = Intent(applicationContext, TelaCadastroPet::class.java)
        val bundle = intent.getBundleExtra("user")!!
        tv_name_user_screen.text = bundle.getString("userName", "").toString()
        tv_uid_user_screen.text = "UID: ${bundle.getInt("userCode", 0).toString()}"
        userCode = bundle.getInt("userCode", 0).toString().toLong()


        val dataBase = Banco.getInstance(this)
        val usuarioDAO = UsuarioDAO(dataBase)
        val petDAO = PetDAO(dataBase)

        petList = ArrayList()


        petList = mostrarPets(petDAO, userCode)
        petAdapter = PetAdapter(petList)
        rvPetUserScreen.adapter = petAdapter

        petAdapter.onItemClick = {
            val chamarTelaEdicaoPet = Intent(applicationContext, TelaEdicaoPet::class.java)
            val bundlePet = Bundle()

            Log.i("Teste", "${it.codigo}")
            //Toast.makeText(this, "${it.codigo}", Toast.LENGTH_SHORT).show()
            var listaDePets = petDAO.selectComWhere(userCode)
            for(pet in listaDePets){
                var dadosPet = pet.split(" - ").toTypedArray()
                //Log.i("Teste", "${dadosUsuario[4]} - ${toolsEncryption.encrypt(et_password_initial_screen.text.toString())}")

                if(dadosPet[0].toInt() == it.codigo){
                    bundlePet.apply {
                        putInt("codePet", dadosPet[0].toString().toInt())
                    }
                    chamarTelaEdicaoPet.putExtra("pet", bundlePet)
                }
            }
            startActivity(chamarTelaEdicaoPet)
        }

        iv_user_screen.setOnClickListener {
            chamarTelaEdicaoUsuario.putExtra("user", bundle)
            startActivity(chamarTelaEdicaoUsuario)
        }

        bt_add_pet_user_screen.setOnClickListener {
            //Log.i("Teste", pets.toString())
            chamarTelaCadastroPet.putExtra("user", bundle)
            startActivity(chamarTelaCadastroPet)
        }

        bt_return_user_screen.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val bundle = intent.getBundleExtra("user")!!
        tv_name_user_screen.text = bundle.getString("userName", "").toString()
        tv_uid_user_screen.text = "UID: ${bundle.getInt("userCode", 0)}"
        userCode = bundle.getInt("userCode", 0).toString().toLong()
        // Atualizar lista de pets ao voltar para esta tela
        val dataBase = Banco.getInstance(this)
        val petDAO = PetDAO(dataBase)
        petList = mostrarPets(petDAO, userCode)
        petAdapter.updateList(petList)
    }

    fun mostrarPets(petDAO: PetDAO, userCode: Long): ArrayList<Pet> {
        val novaLista = ArrayList<Pet>() // Crie uma nova lista local
        val listaDePets = petDAO.selectComWhere(userCode)
        for (valor in listaDePets) {
            val dadosSeparados = valor.split(" - ").toTypedArray()
            val pet = Pet(
                dadosSeparados[0].toInt(),
                dadosSeparados[1],
                dadosSeparados[2],
                dadosSeparados[3],
                dadosSeparados[4],
                dadosSeparados[5].toInt()
            )
            novaLista.add(pet)
        }
        return novaLista
    }
}