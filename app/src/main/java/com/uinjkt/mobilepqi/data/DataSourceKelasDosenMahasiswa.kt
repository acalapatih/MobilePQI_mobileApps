package com.uinjkt.mobilepqi.data

class DataSourceKelasDosenMahasiswa {
    fun dataKelas() : List<DataKelas> {
        return listOf(
            DataKelas("PQI-2C", "4.001", "Senin, 07.30-10.00", 25)
        )
    }

    fun dataDosen() : List<DataDosen> {
        return listOf(
            DataDosen("Dosen1", "NIP", 196903161999000000)
        )
    }
}