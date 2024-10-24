package com.controlevacinacao.extiv

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaCadastroPet : AppCompatActivity() {

    lateinit var bt_return_pet_register_screen: FloatingActionButton
    lateinit var et_name_pet_register_screen: EditText
    lateinit var et_breed_pet_register_screen: EditText
    lateinit var et_birthdate_pet_register_screen: EditText
    lateinit var rg_size_pet_register_screen: RadioGroup
    lateinit var rb_size_small_pet_register_screen: RadioButton
    lateinit var rb_size_medium_pet_register_screen: RadioButton
    lateinit var rb_size_large_pet_register_screen: RadioButton
    lateinit var bt_add_pet_register_screen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_cadastro_pet)


        bt_return_pet_register_screen = findViewById(R.id.bt_return_pet_register_screen)
        et_name_pet_register_screen = findViewById(R.id.et_name_pet_register_screen)
        et_breed_pet_register_screen = findViewById(R.id.et_breed_pet_register_screen)
        et_birthdate_pet_register_screen = findViewById(R.id.et_birthdate_pet_register_screen)
        rg_size_pet_register_screen = findViewById(R.id.rg_size_pet_register_screen)
        rb_size_small_pet_register_screen = findViewById(R.id.rb_size_small_pet_register_screen)
        rb_size_medium_pet_register_screen = findViewById(R.id.rb_size_medium_pet_register_screen)
        rb_size_large_pet_register_screen = findViewById(R.id.rb_size_large_pet_register_screen)
        bt_add_pet_register_screen = findViewById(R.id.bt_add_pet_register_screen)

        val dataBase = Banco(applicationContext)
        val petDAO = PetDAO(dataBase)
        val bundle = intent.getBundleExtra("user")!!


        bt_add_pet_register_screen.setOnClickListener {
            if(et_name_pet_register_screen.text.isBlank() || et_breed_pet_register_screen.text.isBlank() ||
                et_birthdate_pet_register_screen.text.isBlank() || (!rb_size_small_pet_register_screen.isChecked &&
                        !rb_size_medium_pet_register_screen.isChecked && !rb_size_large_pet_register_screen.isChecked)){
                Toast.makeText(this, "Prencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                var size = ""
                if(rb_size_small_pet_register_screen.isChecked){
                    size = "Pequeno"
                }else if(rb_size_medium_pet_register_screen.isChecked){
                    size = "Médio"
                }else if(rb_size_large_pet_register_screen.isChecked){
                    size = "Grande"
                }else{
                    size = "Isso não deveria estar aqui"
                }
                var pet = Pet(0, et_name_pet_register_screen.text.toString(), et_breed_pet_register_screen.text.toString(),
                    size, et_birthdate_pet_register_screen.text.toString(), bundle.getInt("userCode", 0))
                petDAO.insert(pet)
                Log.i("TESTE", "${pet.codigo} - ${pet.nome} - ${pet.raca} - ${pet.porte} - ${pet.dataNascimento} - ${pet.codigo_dono}")
                Toast.makeText(this, "Usuário cadastrado", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
        }

        bt_return_pet_register_screen.setOnClickListener {
            finish()
        }

    }

    fun limparCampos() {
        et_name_pet_register_screen.text.clear()
        et_breed_pet_register_screen.text.clear()
        et_birthdate_pet_register_screen.text.clear()
        rg_size_pet_register_screen.clearCheck()
    }
}