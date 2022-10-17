package com.example.fragmentex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentex.MainActivity
import com.example.fragmentex.R
import com.example.fragmentex.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding : FragmentListBinding
    private var mainActivity : MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            mainActivity = context as MainActivity
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            binding = FragmentListBinding.inflate(inflater, container, false)
            binding.listFragment = this

            binding.textTitle.text = arguments?.getString("key1")
            binding.textValue.text = "${arguments?.getInt("key2")}"

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    fun setValue(value:String) {
        binding.textFromActivity.text = value
    }

    fun onAddToBackStack() {
        val detailFragment = DetailFragment()
        val transaction = mainActivity?.supportFragmentManager!!.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)
        transaction.addToBackStack("detail")
        transaction.commit()
    }
}