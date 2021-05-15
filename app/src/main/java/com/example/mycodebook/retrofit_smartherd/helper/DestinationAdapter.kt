package com.example.mycodebook.retrofit_smartherd.helper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mycodebook.retrofit_smartherd.DestinationDetailActivity
import com.example.mycodebook.retrofit_smartherd.R
import com.example.mycodebook.retrofit_smartherd.databinding.ListItemBinding
import com.example.mycodebook.retrofit_smartherd.models.Destination
import java.util.ArrayList

class DestinationAdapter(private val context : Context , private val destination: List<Destination>) :
    RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {



    class ViewHolder( var itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
          fun bind(destination: Destination){
              itemBinding.txvDestination.text = destination.country
          }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =  ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destinationObj  =  destination[position]
        holder.bind(destinationObj)
        holder.itemBinding.txvDestination.setOnClickListener {
            val intent = Intent(context,DestinationDetailActivity::class.java)
            intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, destinationObj.id)
           // Toast.makeText(context,"${destinationObj.id}",Toast.LENGTH_SHORT).show()
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return destination.size
    }
}