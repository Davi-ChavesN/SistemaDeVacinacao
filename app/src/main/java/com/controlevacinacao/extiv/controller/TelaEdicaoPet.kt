package com.controlevacinacao.extiv.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.PetDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaEdicaoPet : AppCompatActivity() {

    private lateinit var btReturnPetEditScreen : FloatingActionButton
    private lateinit var etNamePetEditScreen: EditText
    private lateinit var etBreedPetEditScreen: EditText
    private lateinit var etBirthdatePetEditScreen: EditText
    private lateinit var btUpdatePetEditScreen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_edicao_pet)

        btReturnPetEditScreen = findViewById(R.id.bt_return_pet_edit_screen)
        etNamePetEditScreen = findViewById(R.id.et_name_pet_edit_screen)
        etBreedPetEditScreen = findViewById(R.id.et_breed_pet_edit_screen)
        etBirthdatePetEditScreen = findViewById(R.id.et_birthdate_pet_edit_screen)
        btUpdatePetEditScreen = findViewById(R.id.bt_update_pet_edit_screen)

        val dataBase = Banco.getInstance(this)
        val petDAO = PetDAO(dataBase)


        btReturnPetEditScreen.setOnClickListener {
            finish()
        }
    }
}