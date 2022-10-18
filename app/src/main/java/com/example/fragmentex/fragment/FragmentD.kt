package com.example.fragmentex.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.fragmentex.databinding.FragmentDBinding
import com.example.fragmentex.vm.FragmentViewModel

class FragmentD : Fragment() {
    private lateinit var binding : FragmentDBinding
    private val viewModel: FragmentViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDBinding.inflate(inflater, container, false)
        binding.fragmentD = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            //LiveData Observing
            viewModel.sampleData.observe(viewLifecycleOwner) { sampleData ->
                binding.textView.text = sampleData.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}