package com.example.energysavingtipsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.energysavingtipsapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsertData : Button
    private lateinit var btnFetchData : Button

    private lateinit var btnCalculateBill : Button
    private lateinit var userFetchTips: Button
    private lateinit var eConsumption: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnInsertData)
        btnFetchData = findViewById(R.id.btnFetchData)

        btnCalculateBill = findViewById(R.id.btnCalculateBill)
        userFetchTips = findViewById(R.id.userFetchTips)
        eConsumption = findViewById(R.id.eConsumption)

        btnInsertData.setOnClickListener{
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener{
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }

        btnCalculateBill.setOnClickListener{
            val intent = Intent(this, CalculateElectricBill::class.java)
            startActivity(intent)
        }

        userFetchTips.setOnClickListener{
            val intent = Intent(this, FetchingActivityUser::class.java)
            startActivity(intent)
        }

        eConsumption.setOnClickListener{
            val intent = Intent(this, EConsumptionCalculator::class.java)
            startActivity(intent)
        }

    }
}