package com.example.mycodebook.retrofit_smartherd.Authorization.BasicAuthorization

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val authToken : String) : Interceptor {


    //method to add Headers with the request
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder().header("Authorization", authToken).build()
        return chain.proceed(request)
    }


}