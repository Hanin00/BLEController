package com.kotiln.capston

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotiln.capston.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
    }
}