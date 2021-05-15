package com.example.mycodebook.retrofit_smartherd.helper

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {


    private const val URL =  "http://127.0.0.1:9000/"

    private val okHttp = OkHttpClient.Builder()

    //create retrofit builder
    private val builder =  Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())

    private val retrofit =  builder.build()

    fun <T> buildServer(serviceType : Class<T>) : T {
        return  retrofit.create(serviceType)
    }


}