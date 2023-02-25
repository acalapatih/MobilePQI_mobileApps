package com.uinjkt.mobilepqi.ui.dashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardSharedViewModel : ViewModel() {

    var latitude: MutableLiveData<Double> = MutableLiveData()
    var longitude: MutableLiveData<Double> = MutableLiveData()
    var location: MutableLiveData<String> = MutableLiveData()

}