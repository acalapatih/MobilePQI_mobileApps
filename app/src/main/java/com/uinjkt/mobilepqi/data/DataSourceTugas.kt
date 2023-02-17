package com.uinjkt.mobilepqi.data

class DataSourceTugas {
    val dataTugas : MutableList<DataTugas> = mutableListOf(
        DataTugas(1, "Tugas 1", "Tenggat Waktu : \n30 Desember 2022 (23:59)" , true),
        DataTugas(2, "Tugas 2", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
        DataTugas(3, "Tugas 3", "Tenggat Waktu : \n30 Desember 2022 (23:59)", true),
        DataTugas(4, "Tugas 4", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
        DataTugas(4, "Tugas 4", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
        DataTugas(5, "Tugas 5", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
        DataTugas(6, "Tugas 6", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
        DataTugas(7, "Tugas 7", "Tenggat Waktu : \n30 Desember 2022 (23:59)", false),
    )

    fun getDataUploadTugas() : MutableList<DataTugas> {
        return mutableListOf(dataTugas[0])
    }
}