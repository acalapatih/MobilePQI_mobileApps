package com.uinjkt.mobilepqi.data


class DataSourceMateriIbadah {
    val dataMateriIbadah : MutableList<DataMateri> = mutableListOf(
        DataMateri(1,"Konsep Ibadah Dalam Islam"),
        DataMateri(2,"Thaharah"),
        DataMateri(3,"Konsep dan Praktik Berbusana dalam Islam"),
        DataMateri(4,"Wirid, Dzikir, dan Doa"),
        DataMateri(5,"Shalat Wajib"),
        DataMateri(6,"Shalat Sunnah"),
        DataMateri(7,"Puasa"),
        DataMateri(8,"Tajhizul Jenazah"),
        DataMateri(9,"Zakat, Infaq, dan Shodaqoh"),
        DataMateri(10,"Haji dan Umrah"),
        DataMateri(11,"Nikah dan Keluarga Dalam Islam"),
        DataMateri(12,"Muamalah"),
        DataMateri(13,"Adab Makan Minum dan Berkomunikasi")
    )


    fun loadDataMenuIbadah() : MutableList<DataMateri> {
        return dataMateriIbadah
    }

    fun addDataMenuIbadah(titleMenuName : String) {
        dataMateriIbadah.add(DataMateri(dataMateriIbadah.size,titleMenuName))
    }
}