package com.bassul.mel.app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bassul.mel.app.databinding.ActivityMainBinding
import com.bassul.mel.app.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = RepositoryAdapter(emptyList())
    }
}
