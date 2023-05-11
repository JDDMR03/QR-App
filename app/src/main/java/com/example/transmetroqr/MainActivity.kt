package com.example.transmetroqr

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre=findViewById<EditText>(R.id.etName)
        val btnAdd=findViewById<Button>(R.id.btnChange)
        val btnGen=findViewById<Button>(R.id.btnGen)
        val ivQR=findViewById<ImageView>(R.id.Qr)

        val sp=getSharedPreferences("myPreference",Context.MODE_PRIVATE)

        val name=sp.getString("name","")

        if(name!=""){
            etNombre.setText(name)
        }

        btnAdd.setOnClickListener {
            val Name=etNombre.text.toString()
            val ed=sp.edit()
            ed.putString("name",Name)
            ed.commit()
        }

        btnGen.setOnClickListener {
            val Name=etNombre.text.toString()
            if(Name==""){
            Toast.makeText(this, "Debe ingresar su nombre primero", Toast.LENGTH_SHORT).show()
            }
            else{
                val encoder= BarcodeEncoder()
                val bit = encoder.encodeBitmap(Name, BarcodeFormat.QR_CODE,411,411)
                ivQR.setImageBitmap(bit)
            }
        }
    }
}

/*
               _   _
              (.)_(.)
           _ (   _   ) _
          / \/`-----'\/ \
        __\ ( (     ) ) /__
        )   /\ \._./ /\   (
         )_/ /|\   /|\ \_(
                JD
 */