package com.example.testkotlinapp.presentation

import android.content.Intent

import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Handler().postDelayed(Runnable { displayData() }, 5000)

    }

    private fun displayData() {
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }
}