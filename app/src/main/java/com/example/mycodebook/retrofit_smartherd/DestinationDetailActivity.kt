package com.example.mycodebook.retrofit_smartherd
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationDetailBinding
import com.example.mycodebook.retrofit_smartherd.helper.DestinationMethods
import com.example.mycodebook.retrofit_smartherd.helper.ServiceBuilder
import com.example.mycodebook.retrofit_smartherd.models.Destination
import retrofit2.Call
import retrofit2.Response


class DestinationDetailActivity : AppCompatActivity() {

  private lateinit var destinationDetailBinding : ActivityDestinationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        destinationDetailBinding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(destinationDetailBinding.root)
        setSupportActionBar(destinationDetailBinding.detailToolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle : Bundle? =  intent.extras

        if(bundle?.containsKey(ARG_ITEM_ID)!!){
            val id = intent.getIntExtra(ARG_ITEM_ID, 0)
            loadDetails(id)
            initUpdateButton(id)
            initDeleteButton(id)
        }

    }

    private fun initDeleteButton(id: Int) {
        destinationDetailBinding.btnDelete.setOnClickListener {

            //retrofit code...
            val service =  ServiceBuilder.buildService(DestinationMethods::class.java)
            val reqCall  =  service.deleteDestination(id)
            reqCall.enqueue( object : retrofit2.Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    TODO("Not yet implemented")
                    finish() // Move back to DestinationListActivity
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun initUpdateButton(id: Int) {
        destinationDetailBinding.btnUpdate.setOnClickListener {
            val city = destinationDetailBinding.etCity.text.toString()
            val description = destinationDetailBinding.etDescription.text.toString()
            val country = destinationDetailBinding.etCountry.text.toString()

            //retrofit code
            val services =  ServiceBuilder.buildService(DestinationMethods::class.java)
            val reqCall =  services.updateDestination(id,city, country, description)
            reqCall.enqueue(object : retrofit2.Callback<Destination>{
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if(response.isSuccessful){
                      //  val updatedDestination =  response.body() use it or ignore it
                        finish() // Move back to DestinationListActivity
                        Toast.makeText(baseContext,"Updated Success", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(baseContext,"Updated Fail", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(baseContext,"Exception occure while updating", Toast.LENGTH_SHORT).show()

                }

            })

        }
    }

    private fun loadDetails(id: Int) {
        // To be replaced by retrofit code


        //add this map to the method for send dynamic headers with the request
//        val map: MutableMap<String, String> = HashMap()
//        map["Page"] = java.lang.String.valueOf("")
//
//        if (BuildConfig.DEBUG) {
//            map["Accept"] = "application/vnd.yourapi.v1.full+json"
//            map["User-Agent"] = "Future Studio Debug"
//        } else {
//            map["Accept"] = "application/json"
//            map["Accept-Charset"] = "utf-8"
//            map["User-Agent"] = "Future Studio Release"
//        }


        val destinationInstance =  ServiceBuilder.buildService(DestinationMethods::class.java)
         val  getDestinById =  destinationInstance.getDestinationById(id)
        getDestinById.enqueue(object : retrofit2.Callback<Destination> {

            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {

                if (response.isSuccessful){
                  val destination = response.body()
                    destination?.let {
                    destinationDetailBinding.etCity.setText(it.city)
                   destinationDetailBinding.etDescription.setText(it.description)
                    destinationDetailBinding.etCountry.setText(it.country)
                   destinationDetailBinding.collapsingToolbar.title =  destination.city
                  }
                }

            }

            override fun onFailure(call: Call<Destination>, t: Throwable) {

            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val ARG_ITEM_ID = "item_id"
    }

}

