package com.uinjkt.mobilepqi.data

class DataSourceJenisTugas {
    val dataJenisTugasSemua : MutableList<DataJenisTugas> = mutableListOf(
        DataJenisTugas(1, "Semua", false),
        DataJenisTugas(2, "Praktikum Ibadah", false),
        DataJenisTugas(3, "Praktikum Qiroah", false),
        DataJenisTugas(4, "Hafalan Surah", false),
        DataJenisTugas(5, "Hafalan Doa", false)
    )

    fun setDataJenisTugasClicked(id: Int) : MutableList<DataJenisTugas> {
        dataJenisTugasSemua[id-1].statusJenisTugas = true
        return dataJenisTugasSemua
    }

}