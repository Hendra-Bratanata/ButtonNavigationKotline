package com.example.ares.buttonnavigation.NetWorkService

import android.util.Log
import java.net.URL

class ApiRepository{

    fun doRequest(url : String):String{
        val data = URL(url).readText()
        Log.d("doRequest",data)
        return data

    }
}