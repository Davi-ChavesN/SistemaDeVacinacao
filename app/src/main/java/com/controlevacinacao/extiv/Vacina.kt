package com.controlevacinacao.extiv

class Vacina(nome: String, descricao: String) {
    companion object {
        private var proximoCodigoVacina = 1
    }

    var codigo: Int
    var nome: String
    var descricao: String
    init {
        this.codigo = proximoCodigoVacina++
        this.nome = nome
        this.descricao = descricao
    }
}