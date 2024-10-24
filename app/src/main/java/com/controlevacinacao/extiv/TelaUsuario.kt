package com.controlevacinacao.extiv

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TelaUsuario : AppCompatActivity() {

    lateinit var bt_return_user_screen: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_usuario)


        bt_return_user_screen = findViewById(R.id.bt_return_user_screen)


        bt_return_user_screen.setOnClickListener {
            finish()
        }
    }
}