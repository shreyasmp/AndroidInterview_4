package com.shreyasmp.blankproject

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/instruments.json")
    fun getInstruments(): Call<List<InstrumentModel>>
}