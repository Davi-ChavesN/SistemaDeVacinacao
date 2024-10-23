package com.controlevacinacao.extiv

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco(context: Context): SQLiteOpenHelper(context, "DataBase", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val tabelaUsuarios = "usuarios"
        val codigoUser = "codigo"
        val nomeUser = "nome"
        val email = "email"
        val usuario = "usuario"
        val senha = "senha"

        val tabelaPets = "pets"
        val codigoPet = "codigo"
        val nomePet = "nome"
        val raca = "raca"
        val codigoUserFK = "codigo_dono"

        val tabelaVacinas = "vacinas"
        val codigo = "codigo"
        val nomeVacina = "nome"
        val descricao = "descricao"

        val tabelaPetVacina = "pet_vacina"
        val codigoPetFK = "codigo_pet"
        val codigoVacinaFK = "codigo_vacina"
        val dataAplicacao = "data_aplicacao"

        val SQL_criacao =
            "CREATE TABLE ${tabelaUsuarios} (" +
                    "${codigoUser} INTEGER PRIMARY KEY," +
                    "${nomeUser} TEXT," +
                    "${email} TEXT," +
                    "${usuario} TEXT," +
                    "${senha} TEXT);" +
            "CREATE TABLE ${tabelaPets} (" +
                    "${codigoPet} INTEGER PRIMARY KEY," +
                    "${nomePet} TEXT," +
                    "${raca} TEXT," +
                    "${codigoUserFK} INTEGER," +
                    "FOREIGN KEY(${codigoUserFK}) REFERENCES ${tabelaUsuarios}(${codigoUser}));" +
            "CREATE TABLE ${tabelaVacinas} (" +
                    "${codigo} INTEGER PRIMARY KEY," +
                    "${nomeVacina} TEXT," +
                    "${descricao} TEXT);" +
            "CREATE TABLE ${tabelaPetVacina} (" +
                    "${codigoPetFK} INTEGER," +
                    "${codigoVacinaFK} INTEGER," +
                    "${dataAplicacao} DATE," +
                    "FOREIGN KEY(${codigoPetFK}) REFERENCES ${tabelaPets}(${codigoPet})," +
                    "FOREIGN KEY(${codigoVacinaFK}) REFERENCES ${tabelaVacinas}(${codigo}));"
        db.execSQL(SQL_criacao)

        val insertVacina = db.compileStatement("INSERT INTO vacinas (nome, descricao) VALUES (?, ?)")
        insertVacina.bindString(1, "Vacina Antirrábica")
        insertVacina.bindString(2, "Protege contra a raiva")
        insertVacina.executeInsert()

        insertVacina.bindString(1, "Vacina V8")
        insertVacina.bindString(2, "Protege contra oito doenças")
        insertVacina.executeInsert()

        insertVacina.bindString(1, "Vacina Giardia")
        insertVacina.bindString(2, "Protege contra giardíase")
        insertVacina.executeInsert()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}