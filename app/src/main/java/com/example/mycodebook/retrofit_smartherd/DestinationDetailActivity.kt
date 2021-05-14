package com.example.mycodebook.retrofit_smartherd
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityDestinationDetailBinding
import com.example.mycodebook.retrofit_smartherd.helper.SampleData
import com.example.mycodebook.retrofit_smartherd.models.Destination


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
            // To be replaced by retrofit code
            SampleData.deleteDestination(id)
            finish() // Move back to DestinationListActivity
        }
    }

    private fun initUpdateButton(id: Int) {
        destinationDetailBinding.btnUpdate.setOnClickListener {
            val city = destinationDetailBinding.etCity.text.toString()
            val description = destinationDetailBinding.etDescription.text.toString()
            val country = destinationDetailBinding.etCountry.text.toString()

            // To be replaced by retrofit code
            val destination = Destination()
            destination.id = id
            destination.city = city
            destination.description = description
            destination.country = country

            SampleData.updateDestination(destination)
            finish() // Move back to DestinationListActivity
        }
    }

    private fun loadDetails(id: Int) {
        // To be replaced by retrofit code
        val destination = SampleData.getDestinationById(id)

        destination?.let {
            destinationDetailBinding.etCity.setText(it.city)
            destinationDetailBinding.etDescription.setText(it.description)
            destinationDetailBinding.etCountry.setText(it.country)
            destinationDetailBinding.collapsingToolbar.title =  destination.city
        }
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

