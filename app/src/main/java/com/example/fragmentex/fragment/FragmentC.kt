package com.example.fragmentex.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentex.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private lateinit var binding : FragmentCBinding
    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = FragmentCBinding.inflate(inflater, container, false)
            binding.fragmentC = this
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    fun setLiveData() {
        count ++
        Log.d("testLog", "setLiveData: $count")
    }
}