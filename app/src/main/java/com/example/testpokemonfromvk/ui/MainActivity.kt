package com.example.testpokemonfromvk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testpokemonfromvk.R
import com.example.testpokemonfromvk.databinding.ActivityMainBinding
import com.example.testpokemonfromvk.ui.listpokemons.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ListFragment())
            .commit()
    }
}