package com.example.mycodebook.retrofit_smartherd.helper

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {


    private const val URL =  "http:192.168.43.176//127.0.0.1:3000/"

    private var okHttp = OkHttpClient.Builder().also {
        it.callTimeout(2,TimeUnit.MINUTES)
        it.connectTimeout(20,TimeUnit.SECONDS)
        it.readTimeout(30,TimeUnit.SECONDS)
        it.writeTimeout(30,TimeUnit.SECONDS)
    }


    //create retrofit builder
    private val builder =  Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())

    private val retrofit =  builder.build()

    fun <T> buildService(serviceType : Class<T>) : T {
        return  retrofit.create(serviceType)
    }


}