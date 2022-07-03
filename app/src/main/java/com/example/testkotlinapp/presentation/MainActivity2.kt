package com.example.testkotlinapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.testkotlinapp.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bu = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        }
    }
}