package com.example.lab3.task5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lab3.R
import kotlinx.android.synthetic.main.fragment_task5_first.view.*

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task5_first, container, false)
        view.to2on1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment1_to_fragment2)
        }
        view.to3on1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment1_to_fragment3)
        }
        return view
    }
}
