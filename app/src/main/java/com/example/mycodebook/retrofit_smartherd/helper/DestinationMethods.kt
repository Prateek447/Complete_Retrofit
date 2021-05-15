package com.example.mycodebook.retrofit_smartherd.helper

import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.http.GET

interface DestinationMethods {


    @GET("Destination")
    fun  getDestination() : Call<List<Destination>>

}