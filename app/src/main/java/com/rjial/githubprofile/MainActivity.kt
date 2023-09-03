package com.rjial.githubprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rjial.githubprofile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView
            .editText
            .setOnEditorActionListener { v, actionId, event ->
                binding.searchBar.text = binding.searchView.text
                binding.searchView.hide()
                Toast.makeText(this@MainActivity, v.text, Toast.LENGTH_SHORT).show()

                false
            }
    }
}