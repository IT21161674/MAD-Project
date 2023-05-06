package com.example.energysavingtipsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.energysavingtipsapp.R

class EConsumptionCalculator : AppCompatActivity() {

    private lateinit var etWattage: EditText
    private lateinit var etHours: EditText
    private lateinit var etCostPerKwh: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_econsumption_calculator)

        etWattage = findViewById(R.id.etWattage)
        etHours = findViewById(R.id.etHours)
        etCostPerKwh = findViewById(R.id.etCostPerKwh)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val wattage = etWattage.text.toString().toDoubleOrNull() ?: 0.0
            val hours = etHours.text.toString().toDoubleOrNull() ?: 0.0
            val costPerKwh = etCostPerKwh.text.toString().toDoubleOrNull() ?: 0.0

            val energyCost = wattage * hours * (costPerKwh * 100)/ 1000
            tvResult.text = "Energy cost: Rs " + String.format("%.2f", energyCost)
        }
    }
}