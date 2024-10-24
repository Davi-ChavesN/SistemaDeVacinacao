package com.controlevacinacao.extiv

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco(context: Context): SQLiteOpenHelper(context, "DataBase", null, 1) {

    companion object {
        @Volatile
        private var INSTANCE: Banco? = null

        fun getInstance(context: Context): Banco {
            return INSTANCE ?: synchronized(this) {
                val instance = Banco(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("PRAGMA foreign_keys=ON;")

        val SQL_criacao1 = """
            CREATE TABLE usuarios (
                codigo INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                email TEXT,
                usuario TEXT,
                senha TEXT
            );
        """
        db.execSQL(SQL_criacao1)

        val SQL_criacao2 = """
            CREATE TABLE pets (
                codigo INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                raca TEXT,
                porte TEXT,
                data_nascimento TEXT,
                codigo_dono INTEGER,
                FOREIGN KEY(codigo_dono) REFERENCES usuarios(codigo)
            );
        """
        db.execSQL(SQL_criacao2)

        val SQL_criacao3 = """
            CREATE TABLE vacinas (
                codigo INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                descricao TEXT
            );
        """
        db.execSQL(SQL_criacao3)

        val SQL_criacao4 = """
            CREATE TABLE pet_vacina (
                codigo_pet INTEGER,
                codigo_vacina INTEGER,
                data_aplicacao DATE,
                FOREIGN KEY(codigo_pet) REFERENCES pets(codigo),
                FOREIGN KEY(codigo_vacina) REFERENCES vacinas(codigo)
            );
        """
        db.execSQL(SQL_criacao4)

        // Insert sample data
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

        // Make sure to insert a user before pets if you have a foreign key constraint
//        val insertUsuario = db.compileStatement("INSERT INTO usuarios (nome, email, usuario, senha) VALUES (?, ?, ?, ?)")
//        insertUsuario.bindString(1, "John Doe")
//        insertUsuario.bindString(2, "john@example.com")
//        insertUsuario.bindString(3, "johndoe")
//        insertUsuario.bindString(4, "password")
//        insertUsuario.executeInsert()
//
//        val insertPet = db.compileStatement("INSERT INTO pets (nome, raca, porte, data_nascimento, codigo_dono) " +
//                "VALUES (?, ?, ?, ?, ?)")
//        insertPet.bindString(1, "Caco")
//        insertPet.bindString(2, "Yorkshire")
//        insertPet.bindString(3, "Pequeno")
//        insertPet.bindString(4, "2007-01-01")
//        insertPet.bindLong(5, 1) // Make sure this user exists
//        insertPet.executeInsert()
//
//        insertPet.bindString(1, "Lady")
//        insertPet.bindString(2, "Poodle")
//        insertPet.bindString(3, "Médio")
//        insertPet.bindString(4, "2009-01-01")
//        insertPet.bindLong(5, 1)
//        insertPet.executeInsert()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS pets")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS vacinas")
        db.execSQL("DROP TABLE IF EXISTS pet_vacina")
        onCreate(db)
    }
}
