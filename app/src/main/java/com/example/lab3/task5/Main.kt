package com.example.lab3.task5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.lab3.R
import kotlinx.android.synthetic.main.task5_main.*

class Main: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task5_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }

}

