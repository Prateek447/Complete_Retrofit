package com.example.mycodebook.retrofit_smartherd.helper

import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationMethods {

 //method 1: get destination simple
    @GET("destination")
    fun  getDestination() : Call<List<Destination>>

  //method 2; get destination according to destination id
    @GET("destination/{id}")
    fun getDestinationById(@Path("id") id:Int): Call<Destination>

    //method 3: get destination according to some condition like get destination where country is india
    // if we didn't pass null while calling this method then this will work similar to method 1
    @GET("destination")
    fun getDestinationByCond(@Query("country") country : String? ) : Call<List<Destination>>

    //method 4: use QueryMap when you have more condition then pass the HashMap in function
    @GET("destination")
    fun getDestinationByQueryMap(@QueryMap filter : HashMap<String, String>) : Call<List<Destination>>


    //method 5 and method : 4 will give the same output
    @GET("destination")
    fun getDes(@Query("country") country: String? , @Query("count") count : String?)

    //method: 6 to post the data to the server....
    @POST("destination")
    fun addDestination(@Body destination: Destination) : Call<Destination>

    //method : 7 put method to update the existing Destination using FormUrlEncode(values put in the url)

    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(@Path("id") id: Int,
                          @Field("city") city : String?,
                          @Field("country") country: String?,
                          @Field("description") description : String?
                          ) : Call<Destination>

}