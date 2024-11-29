package com.controlevacinacao.extiv.controller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.controlevacinacao.extiv.R
import com.controlevacinacao.extiv.banco.Banco
import com.controlevacinacao.extiv.model.Pet
import com.controlevacinacao.extiv.model.PetAdapter
import com.controlevacinacao.extiv.model.PetDAO
import com.controlevacinacao.extiv.model.UsuarioDAO
import com.controlevacinacao.extiv.model.Vacina
import com.controlevacinacao.extiv.model.VacinaAdapter
import com.controlevacinacao.extiv.model.VacinaDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaEdicaoVacinas : AppCompatActivity() {

    private lateinit var btReturnVaccineEditScreen: FloatingActionButton
    private lateinit var btUpdateVaccineEditScreen: Button

    private lateinit var rvVaccineEditScreen: RecyclerView
    private lateinit var vacinaList: ArrayList<Vacina>
    private lateinit var vacinaAdapter: VacinaAdapter

    private val selectedVacinas = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_edicao_vacinas)

        btReturnVaccineEditScreen = findViewById(R.id.btReturnVaccineEditScreen)
        btUpdateVaccineEditScreen = findViewById(R.id.btUpdateVaccineEditScreen)

        rvVaccineEditScreen = findViewById(R.id.rvVaccineEditScreen)
        rvVaccineEditScreen.setHasFixedSize(true)
        rvVaccineEditScreen.layoutManager = LinearLayoutManager(this)


        val bundleVacina = intent.getBundleExtra("vacinas")
        if (bundleVacina == null) {
            Log.i("TelaEdicaoVacinas", "O Bundle 'vacinas' está nulo")
            finish()
            return
        }
        val petCode = bundleVacina.getInt("codePet", 0).toString().toLong()

        val dataBase = Banco.getInstance(this)
        val petDAO = PetDAO(dataBase)
        val vacinaDAO = VacinaDAO(dataBase)

        vacinaList = ArrayList()
        vacinaList = mostrarVacinas(vacinaDAO)
        vacinaAdapter = VacinaAdapter(vacinaList)
        rvVaccineEditScreen.adapter = vacinaAdapter


        btUpdateVaccineEditScreen.setOnClickListener {

        }

        btReturnVaccineEditScreen.setOnClickListener {
            finish()
        }

    }

    fun mostrarVacinas(vacinaDAO: VacinaDAO): ArrayList<Vacina> {
        val novaLista = ArrayList<Vacina>() // Crie uma nova lista local
        val listaDeVacinas = vacinaDAO.select()
        Log.i("mostrar vacinas", "${listaDeVacinas}")
        for (valor in listaDeVacinas) {
            val dadosSeparados = valor.split(" - ")
            if (dadosSeparados.size >= 3) { // Garantir que existam ao menos 3 elementos
                val vacina = Vacina(
                    dadosSeparados[0].toInt(),
                    dadosSeparados[1],
                    dadosSeparados[2]
                )
                novaLista.add(vacina)
            } else {
                Log.i("mostrarVacinas", "Dados inválidos: $valor")
            }
        }
        return novaLista
    }
}