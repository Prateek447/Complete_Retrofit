package com.example.mycodebook.retrofit_smartherd.helper

import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DestinationMethods {


    @GET("destination")
    fun  getDestination() : Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestinationById(@Path("id") id:Int): Call<Destination>

}