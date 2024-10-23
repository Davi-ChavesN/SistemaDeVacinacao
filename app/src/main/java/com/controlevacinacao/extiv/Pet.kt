package com.controlevacinacao.extiv

class Pet(codigo: Int, nome: String, raca: String, codigo_dono: Int) {
    var codigo: Int
    var nome: String
    var raca: String
    var codigo_dono: Int
    init {
        this.codigo = codigo
        this.nome = nome
        this.raca = raca
        this.codigo_dono = codigo_dono
    }
}