package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity1.*

class Activity1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        nav_view.setNavigationItemSelectedListener {
            startActivity(Intent(this, ActivityAbout::class.java))
            return@setNavigationItemSelectedListener true
        }
    }

    fun toSecond(view: View) {
        startActivity(Intent("com.example.lab3.Activity2"))
    }


}
