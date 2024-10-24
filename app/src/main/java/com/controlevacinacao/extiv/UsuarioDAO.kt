package com.controlevacinacao.extiv

import android.content.ContentValues
import android.util.Log

class UsuarioDAO(banco: Banco) {
    var banco: Banco
    init {
        this.banco = banco
    }

    fun insert(usuario: Usuario) {
        val db_insert = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", usuario.nome)
            put("email", usuario.email)
            put("usuario", usuario.usuario)
            put("senha", usuario.senha)
        }
        val confirmaInsert = db_insert?.insert("usuarios", null, cv_valores)
        Log.i("Teste Insercao Usuario", "Insercao: ${confirmaInsert}")
    }

    fun select(): ArrayList<String> {
        var listaUsuarios = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM usuarios", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val usuario = getString(getColumnIndexOrThrow("usuario"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                listaUsuarios.add("${codigo} - ${nome} - ${email} - ${usuario} - ${senha}")
            }
        }
        cursor.close()
        return (listaUsuarios)
    }

    fun selectComWhere(codigo: Long): ArrayList<String> {
        var listaUsuarios = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM usuarios WHERE codigo = ${codigo}", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                val usuario = getString(getColumnIndexOrThrow("usuario"))
                val senha = getString(getColumnIndexOrThrow("senha"))
                listaUsuarios.add("${codigo} - ${nome} - ${email} - ${usuario} - ${senha}")
            }
        }
        cursor.close()
        return (listaUsuarios)
    }

    fun update(usuario: Usuario) {
        val db_update = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", usuario.nome)
            put("email", usuario.email)
            put("usuario", usuario.usuario)
            put("senha", usuario.senha)
        }
        val condicao = "codigo = ${usuario.codigo}"
        val confirmaUpdate = db_update.update("usuarios", cv_valores, condicao, null)
    }

    fun delete(codigo: Int) {
        val db_delete = this.banco.writableDatabase
        val condicao = "codigo = ${codigo}"
        db_delete.delete("usuarios", condicao, null)
    }

}