package com.example.mycodebook.retrofit_smartherd.helper

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceBuilder {


    private const val URL =  "http://127.0.0.1:3000/"


    private val  logger =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


//custom Interceptor to send header at  runtime and it is also used for authentication and error handling
    private val customInterceptor =  object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var  request =  chain.request()
            request.newBuilder()
                .addHeader("x-device-type", Build.TYPE)
                .addHeader("language", Locale.getDefault().language)
                .build()
            val response = chain.proceed(request)
            return response
        }

    }

    private var okHttp = OkHttpClient.Builder().also {
        it.callTimeout(2,TimeUnit.MINUTES)
        it.connectTimeout(20,TimeUnit.SECONDS)
        it.readTimeout(30,TimeUnit.SECONDS)
        it.writeTimeout(30,TimeUnit.SECONDS)
        it.addInterceptor(customInterceptor)
        it.addInterceptor(logger)
    }



    //create retrofit builder
    private val builder =  Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())

    private val retrofit =  builder.build()

    fun <T> buildService(serviceType : Class<T>) : T {
        return  retrofit.create(serviceType)
    }


}