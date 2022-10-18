package com.example.fragmentex.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    private val _sampleData = MutableLiveData(0)
    val sampleData: LiveData<Int>
        get() = _sampleData

    fun controlSampleData() {
        _sampleData.value = _sampleData.value?.inc()
    }
}