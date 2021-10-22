package com.concrete.challenge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.concrete.challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}