package com.example.fragmentex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        try {
            binding = FragmentABinding.inflate(inflater, container, false)
            binding.fragmentA = this


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }


}