package com.example.fragmentex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentex.MainActivity
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentStackBinding

class StackFragment : Fragment() {
    private lateinit var binding : FragmentStackBinding
    private var mainActivity : MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            binding = FragmentStackBinding.inflate(inflater,container,false)
            binding.stackFragment = this

            setFragment()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        return binding.root
    }

    fun onGoBack() {
        mainActivity?.onBackPressed()
    }

    private fun setFragment() {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerA, FragmentA())
            add(R.id.fragmentContainerB, FragmentB())
            setReorderingAllowed(true)
            commit()
        }
    }
}