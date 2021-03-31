package com.example.lab3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity3.nav_view

class Activity3_task4: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3_task4)
        nav_view.setNavigationItemSelectedListener {
            startActivity(Intent(this, ActivityAbout::class.java))
            return@setNavigationItemSelectedListener true
        }
    }

    fun toSecondOnThird_4(view: View) {
        startActivity(Intent(this, Activity2_task4::class.java))
    }

    fun toFirstOnThird_4(view: View) {
        val intent = Intent(this, Activity1_task4::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}