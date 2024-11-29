package com.controlevacinacao.extiv.controller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.Pet
import com.controlevacinacao.extiv.model.PetDAO
import com.controlevacinacao.extiv.model.Usuario
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaEdicaoPet : AppCompatActivity() {

    private lateinit var btReturnPetEditScreen : FloatingActionButton
    private lateinit var etNamePetEditScreen: EditText
    private lateinit var etBreedPetEditScreen: EditText
    private lateinit var etBirthdatePetEditScreen: EditText
    private lateinit var btUpdatePetEditScreen: Button
    private lateinit var btDeletPetEditScreen: Button
    private lateinit var rg_size_pet_edit_screen: RadioGroup
    private lateinit var rb_size_small_pet_edit_screen: RadioButton
    private lateinit var rb_size_medium_pet_edit_screen: RadioButton
    private lateinit var rb_size_large_pet_edit_screen: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_edicao_pet)

        btReturnPetEditScreen = findViewById(R.id.bt_return_pet_edit_screen)
        etNamePetEditScreen = findViewById(R.id.et_name_pet_edit_screen)
        etBreedPetEditScreen = findViewById(R.id.et_breed_pet_edit_screen)
        etBirthdatePetEditScreen = findViewById(R.id.et_birthdate_pet_edit_screen)
        btUpdatePetEditScreen = findViewById(R.id.bt_update_pet_edit_screen)
        btDeletPetEditScreen = findViewById(R.id.btDeletePetEditScreen)
        rg_size_pet_edit_screen = findViewById(R.id.rg_size_pet_edit_screen)
        rb_size_small_pet_edit_screen = findViewById(R.id.rb_size_small_pet_edit_screen)
        rb_size_medium_pet_edit_screen = findViewById(R.id.rb_size_medium_pet_edit_screen)
        rb_size_large_pet_edit_screen = findViewById(R.id.rb_size_big_pet_edit_screen)

        val bundlePet = intent.getBundleExtra("pet")!!
        var petCode = bundlePet.getInt("codePet", 0).toString().toLong()

        val dataBase = Banco.getInstance(this)
        val petDAO = PetDAO(dataBase)

        var pets = petDAO.selectComWhereCodPet(petCode)
        var dadosSeparados = pets[0].split(" - ").toTypedArray()
        var pet = Pet(dadosSeparados[0].toInt(), dadosSeparados[1], dadosSeparados[2],
            dadosSeparados[3], dadosSeparados[4], dadosSeparados[5].toInt())

        etNamePetEditScreen.setText(pet.nome)
        etBreedPetEditScreen.setText(pet.raca)
        etBirthdatePetEditScreen.setText(pet.dataNascimento)
        if(pet.porte.equals("Pequeno")){
            rg_size_pet_edit_screen.check(rb_size_small_pet_edit_screen.id)
        }else if(pet.porte.equals("Médio")){
            rg_size_pet_edit_screen.check(rb_size_medium_pet_edit_screen.id)
        }else if(pet.porte.equals("Grande")){
            rg_size_pet_edit_screen.check(rb_size_large_pet_edit_screen.id)
        }

        btUpdatePetEditScreen.setOnClickListener {
            var nome = etNamePetEditScreen.text.toString()
            var raca = etBreedPetEditScreen.text.toString()
            var dataNascimento = etBirthdatePetEditScreen.text.toString()
            var porte = ""
            if(rb_size_small_pet_edit_screen.isChecked){
                porte = "Pequeno"
            }else if(rb_size_medium_pet_edit_screen.isChecked){
                porte = "Médio"
            }else if(rb_size_large_pet_edit_screen.isChecked){
                porte = "Grande"
            }else{
                porte = "Isso não deveria estar aqui"
            }
            var pet = Pet(pet.codigo, nome, raca, porte, dataNascimento, pet.codigo_dono)
            petDAO.update(pet)
            Log.i("TESTE", "${pet.codigo} - ${pet.nome} - ${pet.raca} - ${pet.porte} - ${pet.dataNascimento} - ${pet.codigo_dono}")
            Toast.makeText(this, "Pet atualizado com sucesso", Toast.LENGTH_SHORT).show()
        }

        btDeletPetEditScreen.setOnClickListener {
            petDAO.delete(pet.codigo)
            Toast.makeText(this, "Pet deletado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }

        btReturnPetEditScreen.setOnClickListener {
            finish()
        }
    }
}