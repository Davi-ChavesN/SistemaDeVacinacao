package com.controlevacinacao.extiv

import android.content.ContentValues
import android.util.Log

class PetDAO(banco: Banco) {
    var banco: Banco
    init {
        this.banco = banco
    }

    fun insert(pet: Pet) {
        val db_insert = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", pet.nome)
            put("raca", pet.raca)
            put("porte", pet.porte)
            put("data_nascimento", pet.dataNascimento)
            put("codigo_dono", pet.codigo_dono)
        }
        val confirmaInsert = db_insert?.insert("pets", null, cv_valores)
        Log.i("Teste Insercao Pet", "Insercao: ${confirmaInsert}")
    }

    fun select(): ArrayList<String> {
        var listaPets = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM pets", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val raca = getString(getColumnIndexOrThrow("raca"))
                val porte = getString(getColumnIndexOrThrow("porte"))
                val dataNascimento = getString(getColumnIndexOrThrow("data_nascimento"))
                val codigo_dono = getLong(getColumnIndexOrThrow("codigo_dono"))
                listaPets.add("${codigo} - ${nome} - ${raca} - ${porte} - ${dataNascimento} - ${codigo_dono}")
            }
        }
        cursor.close()
        return (listaPets)
    }

    fun selectComWhere(codigo: Long): ArrayList<String> {
        var listaPets = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM pets WHERE codigo_dono = ${codigo}", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val raca = getString(getColumnIndexOrThrow("raca"))
                val porte = getString(getColumnIndexOrThrow("porte"))
                val dataNascimento = getString(getColumnIndexOrThrow("data_nascimento"))
                val codigo_dono = getLong(getColumnIndexOrThrow("codigo_dono"))
                listaPets.add("${codigo} - ${nome} - ${raca} - ${porte} - ${dataNascimento} - ${codigo_dono}")
            }
        }
        cursor.close()
        return (listaPets)
    }

    fun update(pet: Pet) {
        val db_update = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", pet.nome)
            put("raca", pet.raca)
            put("porte", pet.porte)
            put("data_nascimento", pet.dataNascimento)
            put("codigo_dono", pet.codigo_dono)
        }
        val condicao = "codigo = ${pet.codigo}"
        val confirmaUpdate = db_update.update("pets", cv_valores, condicao, null)
    }

    fun delete(codigo: Int) {
        val db_delete = this.banco.writableDatabase
        val condicao = "codigo = ${codigo}"
        db_delete.delete("pets", condicao, null)
    }
}