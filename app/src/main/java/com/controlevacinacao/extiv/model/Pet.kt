package com.controlevacinacao.extiv.model

class Pet(codigo: Int, nome: String, raca: String, porte: String, dataNascimento: String, codigo_dono: Int) {
    var codigo: Int
    var nome: String
    var raca: String
    var porte: String
    var dataNascimento: String
    var codigo_dono: Int
    init {
        this.codigo = codigo
        this.nome = nome
        this.raca = raca
        this.porte = porte
        this.dataNascimento = dataNascimento
        this.codigo_dono = codigo_dono
    }
}