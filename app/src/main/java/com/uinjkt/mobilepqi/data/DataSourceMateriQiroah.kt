package com.uinjkt.mobilepqi.data


class DataSourceMateriQiroah {

    val dataMateriQiroah : MutableList<DataMateri> = mutableListOf(
        DataMateri(1,"Hukum Nun Sukun dan Tanwin"),
        DataMateri(2,"Hukum Mim Sukun"),
        DataMateri(3,"Macam-macam Idgham"),
        DataMateri(4,"Waqaf"),
        DataMateri(5,"Mad"),
        DataMateri(6,"Qalqalah")
    )

    fun loadDataMenuQiroah() : MutableList<DataMateri> {
        return dataMateriQiroah
    }

    fun addDataMenuQiroah(titleMenuName : String) {
        dataMateriQiroah.add(DataMateri(dataMateriQiroah.size,titleMenuName))
    }
}