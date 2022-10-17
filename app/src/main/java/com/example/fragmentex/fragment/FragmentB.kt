package com.example.fragmentex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentex.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private lateinit var binding : FragmentBBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = FragmentBBinding.inflate(inflater, container, false)
            binding.fragmentB = this
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }
}