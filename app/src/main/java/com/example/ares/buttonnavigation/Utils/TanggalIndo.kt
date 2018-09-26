package com.example.ares.buttonnavigation.Utils

import java.text.SimpleDateFormat

class TanggalIndo {
    fun ambilTanggal(date :String?):String?{
        val formatter = SimpleDateFormat("dd/MM/yy").parse(date)
        val tanggal = formatter.toString()
        var hari = tanggal.substring(0,3)
        val bulan = tanggal.substring(3,7)
        val tgls = tanggal.substring(7,10)
        val th = tanggal.substring(29)
        when(hari){
            "Sun" -> { hari = "Minggu" }
            "Mon" -> { hari = "Senin" }
            "Tue" -> { hari = "Selasa" }
            "Wed" -> { hari = "Rabu" }
            "Thu" -> { hari = "Kamis" }
            "Fri" -> { hari = "Jumat" }
            "Sat" -> { hari = "Sabtu" }
        }
        val tglIndo = "$hari, $tgls $bulan $th"
        return tglIndo
    }
    }
