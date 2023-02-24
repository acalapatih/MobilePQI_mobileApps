package com.uinjkt.mobilepqi.ui.dashboard.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardSharedViewModel : ViewModel() {

    var latitude: MutableLiveData<Double> = MutableLiveData()
    var longitude: MutableLiveData<Double> = MutableLiveData()
    var location: MutableLiveData<String> = MutableLiveData()

    val a = latitude.combineWith(longitude)

    fun <T, S> MutableLiveData<T>.combineWith(other: MutableLiveData<S>): MutableLiveData<Pair<T?, S?>> =
        MediatorLiveData<Pair<T?, S?>>().apply {
            addSource(this@combineWith) { value = Pair(it, other.value) }
            addSource(other) { value = Pair(this@combineWith.value, it) }
        }
}