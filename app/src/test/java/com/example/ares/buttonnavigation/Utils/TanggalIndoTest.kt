package com.example.ares.buttonnavigation.Utils

import android.annotation.SuppressLint
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TanggalIndoTest {
    private val tanggal = TanggalIndo()

    @Test
    fun testambilTanggal() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date : Date = dateFormat.parse("02/28/2018")

        assertEquals("Rab, 28 Feb 2018",tanggal.ambilTanggal(date))



    }
}