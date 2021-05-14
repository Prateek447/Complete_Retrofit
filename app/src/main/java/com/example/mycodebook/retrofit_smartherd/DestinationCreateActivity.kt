package com.example.mycodebook.retrofit_smartherd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationCreateBinding
import com.example.mycodebook.retrofit_smartherd.helper.SampleData
import com.example.mycodebook.retrofit_smartherd.models.Destination

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
            SampleData.addDestination(data)
            finish() // Move back to DestinationListActivity
        }
    }
}