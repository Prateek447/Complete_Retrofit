package com.example.mycodebook.retrofit_smartherd.Authorization.BasicAuthorization

import android.text.TextUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import org.w3c.dom.Text
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset


object ServiceGenerator {

    private const val API_BASE_URL = "https://your.api-base.url"


    //okHttpInstance
    private var httpClient = OkHttpClient.Builder()
    //retrofit instance with Gson convertor
    private var builder = Retrofit.Builder().baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
   //build retrofit object
   private var retrofit = builder.build()


    //method to create services
    fun <T> createService(serviceClass: Class<T>) : T {
        return createService(serviceClass,null,null)
    }

    //method to check service will create with or without token if username and password provide
    //then only authentication take place
    private fun <T> createService(serviceClass: Class<T>, username : String?, password : String?) : T{
        if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
            val authToken = Credentials.basic(username!!,password!!, Charset.defaultCharset())
            return  createService(serviceClass,authToken)
        }
        return createService(serviceClass,null)
    }

    //method to create service if token is provide and if token is not provide then it
    //will create service without token
    private fun <T> createService(serviceClass: Class<T>, authToken: String?) : T {
        if (!TextUtils.isEmpty(authToken)){
            val interceptor =  AuthenticationInterceptor(authToken!!)
            if(!httpClient.interceptors().contains(interceptor)){
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit =  builder.build()
            }
        }
        return retrofit.create(serviceClass)
    }


}