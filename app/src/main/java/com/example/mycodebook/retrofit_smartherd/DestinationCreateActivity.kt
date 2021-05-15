package com.example.mycodebook.retrofit_smartherd

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationCreateBinding
import com.example.mycodebook.retrofit_smartherd.helper.DestinationMethods
import com.example.mycodebook.retrofit_smartherd.helper.SampleData
import com.example.mycodebook.retrofit_smartherd.helper.ServiceBuilder
import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val createBinding =  ActivityDestinationCreateBinding.inflate(layoutInflater)
        setContentView(createBinding.root)
        setSupportActionBar(createBinding.toolbar)
        createBinding.btnAdd.setOnClickListener {
            val data  =  Destination()
            data.city =  createBinding.etCity.text.toString()
            data.country =  createBinding.etCountry.text.toString()
            data.description = createBinding.etDescription.text.toString()

            // To be replaced by retrofit code
          //  SampleData.addDestination(data)
         //   finish() // Move back to DestinationListActivity

            val destination =  ServiceBuilder.buildService(DestinationMethods::class.java)
            val reqCall  =  destination.addDestination(data)
            reqCall.enqueue(object : retrofit2.Callback<Destination>{
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if(response.isSuccessful){
                        //can do the task with the response

                        Toast.makeText(baseContext,"Successfuly added",Toast.LENGTH_SHORT).show()
                        finish()

                    }
                    else{
                        Toast.makeText(baseContext,"Not added",Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(baseContext,"Exception while added",Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}