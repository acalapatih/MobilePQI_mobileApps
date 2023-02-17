package com.uinjkt.mobilepqi.data

class DataSourceJenisTugas {
    val dataJenisTugasSemua : MutableList<DataJenisTugas> = mutableListOf(
        DataJenisTugas(1, "Semua", false),
        DataJenisTugas(2, "Praktikum Qiroah", false),
        DataJenisTugas(3, "Praktikum Ibadah", false),
        DataJenisTugas(4, "Hafalan Surah", false),
        DataJenisTugas(5, "Hafalan Doa", false)
    )

    fun setDataJenisTugasClicked(id: Int) : MutableList<DataJenisTugas> {
        dataJenisTugasSemua[id-1].statusJenisTugas = true
        return dataJenisTugasSemua
    }

    fun getDataJenisTugas(idJenisTugasClicked: Int) : MutableList<DataJenisTugas> {
        val newDataJenisTugas = setDataJenisTugasClicked(idJenisTugasClicked)
        newDataJenisTugas.removeAt(0)
        return newDataJenisTugas
    }

}