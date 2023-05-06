package com.example.energysavingtipsapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.energysavingtipsapp.R
import com.example.energysavingtipsapp.models.TipModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity(){
    private lateinit var enterTipName: EditText
    private lateinit var enterTipDescription: EditText
    private lateinit var btnSaveTip: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        enterTipName = findViewById(R.id.enterTipName)
        enterTipDescription = findViewById(R.id.enterTipDescription)
        btnSaveTip = findViewById(R.id.btnSaveTip)

        dbRef = FirebaseDatabase.getInstance().getReference("Tips")

        btnSaveTip.setOnClickListener{
            saveTipData()
        }
    }
    private fun saveTipData(){
        var tipName = enterTipName.text.toString()
        var tipDescription = enterTipDescription.text.toString()

        if (tipName.isEmpty()){
            enterTipName.error = "Please enter tip name"
        }

        if (tipDescription.isEmpty()){
            enterTipDescription.error = "Please enter tip description"
        }

        val tipId = dbRef.push().key!!

        var tip = TipModel(tipId, tipName, tipDescription)

        dbRef.child(tipId).setValue(tip)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()
                enterTipName.text.clear()
                enterTipDescription.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
