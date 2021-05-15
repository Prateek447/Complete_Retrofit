package com.example.mycodebook.retrofit_smartherd

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationListBinding
import com.example.mycodebook.retrofit_smartherd.helper.DestinationAdapter
import com.example.mycodebook.retrofit_smartherd.helper.DestinationMethods
import com.example.mycodebook.retrofit_smartherd.helper.ServiceBuilder
import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


class DestinationListActivity : AppCompatActivity() {

    private lateinit var destinationListBinding: ActivityDestinationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        destinationListBinding = ActivityDestinationListBinding.inflate(layoutInflater)
        setContentView(destinationListBinding.root)
        setSupportActionBar(destinationListBinding.toolbar)
        //  supportActionBar?.setDisplayHomeAsUpEnabled(true)

        destinationListBinding.fab.setOnClickListener {
            val intent = Intent(this, DestinationCreateActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    private fun loadDestinations() {
        // To be replaced by retrofit code
//        destinationListBinding.destinyRecyclerView.adapter =  DestinationAdapter(this,SampleData.DESTINATIONS)
//        destinationListBinding.destinyRecyclerView.layoutManager = LinearLayoutManager(this)

        val services = ServiceBuilder.buildService(DestinationMethods::class.java)

        val reqCall = services.getDestination()

        var destinationList: List<Destination>

        reqCall.enqueue(object : Callback<List<Destination>> {
            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                if (response.isSuccessful) {

                    Toast.makeText(this@DestinationListActivity, "success", Toast.LENGTH_SHORT)
                        .show()

                    destinationList = response.body()!!
                    destinationListBinding.destinyRecyclerView.adapter = DestinationAdapter(
                        this@DestinationListActivity,
                        destinationList
                    )
                    destinationListBinding.destinyRecyclerView.layoutManager =
                        LinearLayoutManager(this@DestinationListActivity)

                } else {
                    Toast.makeText(
                        this@DestinationListActivity,
                        "Fail to retrive",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            }

            override fun onFailure(call: Call<List<Destination>>, error: Throwable) {
                if (error is SocketTimeoutException) {
                    // "Connection Timeout";
                    Toast.makeText(
                        this@DestinationListActivity,
                        "Connection Timeout",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (error is IOException) {
                    // "Timeout";
                    Toast.makeText(this@DestinationListActivity, "TimeOut", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    //Call was cancelled by user
                    if (call.isCanceled()) {
                        println("Call was cancelled forcefully");
                    } else {
                        //Generic error handling
                        Toast.makeText(
                            this@DestinationListActivity,
                            "Network Error ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            }
        })
    }
}