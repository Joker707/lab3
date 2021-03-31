package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity1.*

class Activity1_task4: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1_task4)

        nav_view.setNavigationItemSelectedListener {
            startActivity(Intent(this, ActivityAbout::class.java))
            return@setNavigationItemSelectedListener true
        }
    }

    fun toSecond_4(view: View) {
        startActivity(Intent("com.example.lab3.Activity2_task4"))
    }

    fun toThird_4(view: View) {
        val intent = Intent(this, Activity3_task4::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
    }

}