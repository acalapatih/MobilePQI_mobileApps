package com.uinjkt.mobilepqi.data

class DataSourceKelasDosenMahasiswa {
    fun dataKelas() : MutableList<DataKelas> {
        return mutableListOf(
            DataKelas(1, "PQI-2C", "4.001", "Senin, 07.30-10.00", 25)
        )
    }

    fun dataDosen() : MutableList<DataDosen> {
        return mutableListOf(
            DataDosen(1, "Dosen1", "NIP", 196903161999000000)
        )
    }

    fun dataMahasiswa() : MutableList<DataMahasiswa> {
        return mutableListOf(
            DataMahasiswa(1, "Mahasiswa1", "NIP", 196903161999000000)
        )
    }
}