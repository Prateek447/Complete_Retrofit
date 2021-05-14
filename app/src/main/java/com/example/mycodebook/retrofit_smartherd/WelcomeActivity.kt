package com.example.mycodebook.retrofit_smartherd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mycodebook.retrofit_smartherd.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup binding
        val welcomeBinding  =  ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(welcomeBinding.root)
        welcomeBinding.message.text = "Black Friday! Get 50% cash back on saving your first spot."
        welcomeBinding.button.setOnClickListener {
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
            val intent  = Intent(this,DestinationListActivity::class.java)
            startActivity(intent)
        }


    }
}