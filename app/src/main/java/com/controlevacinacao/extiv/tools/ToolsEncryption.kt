package com.controlevacinacao.extiv.tools

import java.security.MessageDigest

class ToolsEncryption {

    public fun encrypt(string: String): String {
        return hashString(string, "SHA-256")
    }

    private fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }

}