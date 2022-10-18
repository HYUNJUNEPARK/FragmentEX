package com.example.fragmentex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentReplaceBinding

class ReplaceFragment : Fragment() {
    private lateinit var binding: FragmentReplaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        try {
            binding = FragmentReplaceBinding.inflate(inflater, container, false)
            binding.replaceFragment = this

            setChildFragment()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    private fun setChildFragment() {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerC, FragmentC())
            add(R.id.fragmentContainerD, FragmentD())
            setReorderingAllowed(true)
            commit()
        }
    }
}