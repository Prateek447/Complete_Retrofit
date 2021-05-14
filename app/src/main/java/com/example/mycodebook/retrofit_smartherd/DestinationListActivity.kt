package com.example.mycodebook.retrofit_smartherd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationListBinding
import com.example.mycodebook.retrofit_smartherd.helper.DestinationAdapter
import com.example.mycodebook.retrofit_smartherd.helper.SampleData
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DestinationListActivity : AppCompatActivity() {

   private lateinit var  destinationListBinding : ActivityDestinationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        destinationListBinding =  ActivityDestinationListBinding.inflate(layoutInflater)
        setContentView(destinationListBinding.root)
        setSupportActionBar(destinationListBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        destinationListBinding.fab.setOnClickListener {
            val intent =  Intent(this,DestinationCreateActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    private fun loadDestinations() {
        // To be replaced by retrofit code
        destinationListBinding.destinyRecyclerView.adapter =  DestinationAdapter(this,SampleData.DESTINATIONS)
        destinationListBinding.destinyRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}