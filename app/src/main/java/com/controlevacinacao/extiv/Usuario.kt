package com.controlevacinacao.extiv

class Usuario(codigo: Int, nome: String, email: String, usuario: String, senha: String) {
    var codigo: Int
    var nome: String
    var email: String
    var usuario: String
    var senha: String
    init {
        this.codigo = codigo
        this.nome = nome
        this.email = email
        this.usuario = usuario
        this.senha = senha
    }
}