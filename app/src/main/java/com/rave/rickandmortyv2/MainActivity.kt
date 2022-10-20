package com.rave.rickandmortyv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rave.rickandmortyv2.databinding.ActivityMainBinding
import com.rave.rickandmortyv2.databinding.SplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val splashBinding by lazy { SplashScreenBinding.inflate(layoutInflater) }
    private val mainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(splashBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(mainBinding.root)
        }, 3000L)

    }

}