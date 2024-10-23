package com.controlevacinacao.extiv

class Usuario(nome: String, email: String, usuario: String, senha: String) {
    companion object {
        private var proximoCodigo = 1
    }

    var codigo: Int
    var nome: String
    var email: String
    var usuario: String
    var senha: String
    init {
        this.codigo = proximoCodigo++
        this.nome = nome
        this.email = email
        this.usuario = usuario
        this.senha = senha
    }
}