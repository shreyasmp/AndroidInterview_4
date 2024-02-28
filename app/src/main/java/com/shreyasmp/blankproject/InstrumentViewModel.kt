package com.shreyasmp.blankproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstrumentViewModel : ViewModel() {

    val instrumentList = mutableListOf<InstrumentModel>()

    init {
        getInstruments()
    }

    private fun getInstruments() {

        viewModelScope.launch {
            val service = Service().provideRetrofit().create(Api::class.java)
            val call = service.getInstruments()
            call.enqueue(object : Callback<List<InstrumentModel>> {
                override fun onResponse(
                    call: Call<List<InstrumentModel>>,
                    response: Response<List<InstrumentModel>>
                ) {
                    Log.d("ViewModel: ", "Success Response: " + response.body()?.toString())
                    response.body()?.forEach { instrument ->
                        instrumentList.add(instrument)
                    }
                }

                override fun onFailure(call: Call<List<InstrumentModel>>, t: Throwable) {
                    Log.d("ViewModel: ", "Error On Network Call")
                }
            })
        }
    }
}