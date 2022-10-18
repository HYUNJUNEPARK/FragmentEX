package com.example.fragmentex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentex.databinding.FragmentDBinding

class FragmentD : Fragment() {
    private lateinit var binding : FragmentDBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = FragmentDBinding.inflate(inflater, container, false)
            binding.fragmentD = this

            //TODO Observe LiveData

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }
}