package com.example.energysavingtipsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.energysavingtipsapp.R

class CalculateElectricBill : AppCompatActivity() {

    private lateinit var etUnitsUsed:EditText
    private lateinit var tvBillAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_electric_bill)

        etUnitsUsed = findViewById(R.id.etUnitsUsed)
        tvBillAmount = findViewById(R.id.tvBillAmount)

        etUnitsUsed.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                computeBillAmount()
            }

        })
    }

    private fun computeBillAmount() {

        if(etUnitsUsed.text.isEmpty()){
            tvBillAmount.text = ""
            return
        }

        //1.Get the value of the used units
        val unitAmount = etUnitsUsed.text.toString().toDouble()
        //2.Calculate the bill
        var totalBill = 0.0

        if(unitAmount <= 30){
            totalBill = 400.00 + (30.00 * unitAmount)//max 1300
        }else if(unitAmount <= 60){
            totalBill = 550.00 + (30.0 * 30) + (37.0 * (unitAmount - 30))//max 2560
        }else if(unitAmount <= 90){
            totalBill = 550.00 + (30.0 * 30) + (37.0 * 30) + (42.0 * (unitAmount - 60))//3820
        }else if(unitAmount <= 120){
            totalBill = 650.00 + (30.0 * 30) + (37.0 * 30) + (42.0 * 30) + (42.0 * (unitAmount - 90))//5180
        }else if(unitAmount <= 180){
            totalBill = 1500.00 + (30.0 * 30) + (37.0 * 30) + (42.0 * 30) + (42.0 * 30)  + (50.0 * (unitAmount - 120))//9030
        }else{
            totalBill = 2000.00 + (30.0 * 30) + (37.0 * 30) + (42.0 * 30) + (42.0 * 30)  + (50.0 * 60) + (75.0 * (unitAmount - 180))
        }

        //3.Update the UI
        tvBillAmount.text = "%.2f".format(totalBill)
    }
}