package com.firstapplication.mars.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firstapplication.mars.databinding.ActivityMainBinding
import com.firstapplication.mars.di.ActivitySubComponent
import com.firstapplication.mars.extensions.appComponent

class MainActivity : AppCompatActivity() {

    var activityComponent: ActivitySubComponent? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appToolbar.toolbar)

        activityComponent = appComponent.activityComponentBuilder().build()
    }

    override fun onDestroy() {
        super.onDestroy()
//        activityComponent = null
    }
}