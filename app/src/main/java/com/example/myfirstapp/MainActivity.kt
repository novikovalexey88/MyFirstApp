package com.example.myfirstapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private  lateinit var callButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callButton = findViewById(R.id.my_awesome_button)

        callButton.setOnClickListener {
           if (isCallPermissionGranted()) {
               callEmergencyNumber()
           } else {
               requestCallPermosson()

           }
        }

    }

    private fun isCallPermissionGranted(): Boolean {
      return  ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCallPermosson() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE)
    }

    private fun callEmergencyNumber(){
        val callUri = Uri.parse("tel://911")
        val callIntent = Intent(Intent.ACTION_CALL, callUri)
        startActivity(callIntent)
    }


    private companion object {
        private const val REQUEST_CODE = 100
    }
}