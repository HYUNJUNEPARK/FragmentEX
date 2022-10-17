package com.example.fragmentex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fragmentex.databinding.ActivityMainBinding
import com.example.fragmentex.fragment.ListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        listFragment = ListFragment()

        /*
         * savedInstanceState 가 Null 일 경우에만 fragmentTransaction 을 생성
         * 액티비티가 처음 만들어질 때, 프래그먼트가 한 번만 추가되는 것을 보장
         */
        if (savedInstanceState == null) {
            setFragment()
            sendData()
        }

    }

    /*
     * 프래그먼트 트랜잭션을 생성하는 메서드
     */
    private fun setFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView, listFragment)
            setReorderingAllowed(true)
            commit()
        }
    }

    /*
     * 프래그먼트 생성 시 데이터 전달하는 메서드
     */
    private fun sendData() {
        val bundle = Bundle()
        bundle.putString("data1", "프래그먼트 생성 시 전달받은 데이터")
        bundle.putInt("data2", 1010101)
        listFragment.arguments = bundle
    }

    fun onSetValue() {
        listFragment.setValue("ListFragment 가 전달받은 데이터")
    }
}