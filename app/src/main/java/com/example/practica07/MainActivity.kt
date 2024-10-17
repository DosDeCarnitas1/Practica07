package com.example.practica07

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun lector(view: View?) {
        val lector_act = Intent(applicationContext,
            LectorActivity::class.java)
        startActivity(lector_act)
    }

    fun camara(view: View?) {
        val foto_act = Intent(applicationContext, FotoActivity::class.java)
        startActivity(foto_act)
    }
}