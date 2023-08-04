package com.firstapplication.mars.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firstapplication.mars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.appToolbar.toolbar)
    }
}