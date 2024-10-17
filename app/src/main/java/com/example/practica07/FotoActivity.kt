package com.example.practica07

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class FotoActivity : AppCompatActivity() {

    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)

        foto = findViewById(R.id.imgFoto)
        btnTomar = findViewById(R.id.btnTomar)

        btnTomar.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            responseLauncher.launch(intent)
        }
    }

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            if(activityResult.resultCode == RESULT_OK){
                Toast.makeText(this, "Fotografía tomada!!!", Toast.LENGTH_SHORT).show()
                val extras = activityResult.data!!.extras
                val imgBitmap = extras!!["data"] as Bitmap?
                foto.setImageBitmap(imgBitmap)
            } else {
                Toast.makeText(this, "Proceso cancelado", Toast.LENGTH_SHORT).show()
            }
        }
}