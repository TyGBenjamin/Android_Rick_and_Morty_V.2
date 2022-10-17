package com.rave.rickandmortyv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rave.lib_data.TestObj

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d("Logger", "onCreate: ${TestObj.TEST}")
  }
}