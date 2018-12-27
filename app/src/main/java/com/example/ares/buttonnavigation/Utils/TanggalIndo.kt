package com.example.ares.buttonnavigation.Utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TanggalIndo {
    @SuppressLint("SimpleDateFormat")
    fun ambilTanggal(date : Date):String?{
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy")
        return formatter.format(date)
    }
    }
