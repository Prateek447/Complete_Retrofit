package com.example.mycodebook.retrofit_smartherd.helper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface AnotherServer {


    //only get because we will pass here complete url
    @GET
    fun getMessage(@Url anotherUrl : String) : Call<String>

    /* Now everything is same but instead of passing DestinationMethod interface
    we will pass AnotherServer interface in buildService method of Service Builder
     */

}