package com.example.lab3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity2.nav_view

class Activity2_task4: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_task4)
        nav_view.setNavigationItemSelectedListener {
            startActivity(Intent(this, ActivityAbout::class.java))
            return@setNavigationItemSelectedListener true
        }
    }

    fun toFirst_4(view: View) {
        finish()
    }

    fun toThirdOnSecond_4(view: View) {
        startActivity(Intent(this, Activity3_task4::class.java))
    }

}