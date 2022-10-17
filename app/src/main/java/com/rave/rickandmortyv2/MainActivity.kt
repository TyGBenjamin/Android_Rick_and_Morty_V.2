package com.rave.rickandmortyv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rave.rickandmortyv2.R
import com.example.lib_data.Test
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d("Logger", "onCreate: ${Test.TEST} ")
  }
}