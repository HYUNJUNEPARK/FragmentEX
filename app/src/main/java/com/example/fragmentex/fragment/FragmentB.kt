package com.example.fragmentex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private lateinit var binding : FragmentBBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = FragmentBBinding.inflate(inflater, container, false)
            binding.fragmentB = this

            setFragmentResultListener(getString(R.string.requestKey)) { /*requestKey*/_, bundle ->
                binding.textView.text = bundle.getString(getString(R.string.bundleKey), "NULL")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }
}