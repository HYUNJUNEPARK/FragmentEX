package com.example.fragmentex.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.fragmentex.databinding.FragmentCBinding
import com.example.fragmentex.vm.FragmentViewModel

class FragmentC : Fragment() {
    private lateinit var binding : FragmentCBinding
    private val viewModel: FragmentViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCBinding.inflate(inflater, container, false)
        binding.fragmentC = this
        binding.viewModel = viewModel
        return binding.root
    }

    fun setLiveData() {
        viewModel.controlSampleData()
    }
}