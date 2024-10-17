package com.example.practica07

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class LectorActivity : AppCompatActivity() {

    //Instancias
    private lateinit var codigo: EditText
    private lateinit var descripcion: EditText
    private lateinit var btnEscanear: Button
    private lateinit var btnCapturar: Button
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector)

        codigo = findViewById(R.id.edtCodigo)
        descripcion = findViewById(R.id.edtDescripcion)
        btnEscanear = findViewById(R.id.btnEscanear)
        btnCapturar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        btnEscanear.setOnClickListener { escanearCodigo() }
        btnCapturar.setOnClickListener {
            if (codigo.text.toString().isNotEmpty() && descripcion.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Datos capturados", Toast.LENGTH_SHORT).show()
                limpiar()
            } else {
                Toast.makeText(this, "Debe registrar datos", Toast.LENGTH_LONG).show()
            }
        }
        btnLimpiar.setOnClickListener { limpiar() }
    }

    private fun escanearCodigo() {

        val intentIntegrator = IntentIntegrator(this@LectorActivity)

        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        intentIntegrator.setPrompt("Lector de códigos")
        intentIntegrator.setCameraId(0)
        intentIntegrator.setBeepEnabled(true)
        intentIntegrator.setBarcodeImageEnabled(true)
        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val intentResult = IntentIntegrator.parseActivityResult(requestCode,
            resultCode, data)

        if (intentResult != null) {

            if (intentResult.contents == null) {

                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Código leído", Toast.LENGTH_SHORT).show()

                codigo.setText(intentResult.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun limpiar() {
        codigo.setText("")
        descripcion.setText("")
        codigo.requestFocus()
    }
}