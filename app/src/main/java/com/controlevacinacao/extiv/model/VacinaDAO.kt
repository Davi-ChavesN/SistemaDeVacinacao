package com.controlevacinacao.extiv.model

import android.content.ContentValues
import android.util.Log
import com.controlevacinacao.extiv.banco.Banco

class VacinaDAO(banco: Banco) {
    var banco: Banco
    init {
        this.banco = banco
    }

    fun insert(vacina: Vacina) {
        val db_insert =this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("codigo", vacina.codigo)
            put("nome", vacina.nome)
            put("raca", vacina.descricao)
        }
        val confirmaInsert = db_insert?.insert("vacinas", null, cv_valores)
        Log.i("Teste Insercao Vacina", "Insercao: ${confirmaInsert}")
    }

    fun select(): ArrayList<String> {
        var listaVacinas = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM vacinas", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val descricao = getString(getColumnIndexOrThrow("descricao"))
                listaVacinas.add("${codigo} - ${nome} - ${descricao}")
            }
        }
        cursor.close()
        return (listaVacinas)
    }

    fun selectComWhere(codigo: Long): ArrayList<String> {
        var listaVacinas = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM vacinas WHERE codigo = ${codigo}", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val descricao = getString(getColumnIndexOrThrow("descricao"))
                listaVacinas.add("${codigo} - ${nome} - ${descricao}")
            }
        }
        cursor.close()
        return (listaVacinas)
    }

    fun update(vacina: Vacina) {
        val db_update = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", vacina.nome)
            put("raca", vacina.descricao)
        }
        val condicao = "codigo = ${vacina.codigo}"
        val confirmaUpdate = db_update.update("vacinas", cv_valores, condicao, null)
    }

    fun delete(codigo: Int) {
        val db_delete = this.banco.writableDatabase
        val condicao = "codigo = ${codigo}"
        db_delete.delete("vacinas", condicao, null)
    }
}