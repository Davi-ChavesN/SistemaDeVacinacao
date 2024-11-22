package com.controlevacinacao.extiv.model

class Vacina(codigo: Int, nome: String, descricao: String) {
    var codigo: Int
    var nome: String
    var descricao: String
    init {
        this.codigo = codigo
        this.nome = nome
        this.descricao = descricao
    }
}