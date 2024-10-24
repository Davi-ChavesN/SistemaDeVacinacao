package com.controlevacinacao.extiv

class Pet(nome: String, raca: String, codigo_dono: Int) {
    companion object {
        private var proximoCodigoPet = 1
    }

    var codigo: Int
    var nome: String
    var raca: String
    var codigo_dono: Int
    init {
        this.codigo = proximoCodigoPet++
        this.nome = nome
        this.raca = raca
        this.codigo_dono = codigo_dono
    }
}