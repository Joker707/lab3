package com.example.lab3.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.lab3.R
import kotlinx.android.synthetic.main.fragment_task5_second.view.*

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task5_second, container, false)
        view.to1on2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment2_to_fragment1)
        }
        view.to3on2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment2_to_fragment3)
        }
        return view
    }
}
