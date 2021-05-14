package com.example.mycodebook.retrofit_smartherd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val destinationListBinding =  ActivityDestinationListBinding.inflate(layoutInflater)
        setContentView(destinationListBinding.root)
    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }

    private fun loadDestinations() {
        // To be replaced by retrofit code
    }
}