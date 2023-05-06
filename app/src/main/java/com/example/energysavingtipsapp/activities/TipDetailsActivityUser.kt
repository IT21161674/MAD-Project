package com.example.energysavingtipsapp.activities

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.energysavingtipsapp.R
import com.example.energysavingtipsapp.models.TipModel
import com.google.firebase.database.FirebaseDatabase

class TipDetailsActivityUser : AppCompatActivity() {

    //defining variables
    private lateinit var tvTipId: TextView
    private lateinit var tvTipName: TextView
    private lateinit var tvTipDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_details_user)

        //calling initView and setValuesToViews functions
        initView()
        setValuesToViews()

//        btnUpdate.setOnClickListener{
//            openUpdateDialog(
//                intent.getStringExtra("tipId").toString(),
//                intent.getStringExtra("tipName").toString()
//
//            )
//        }
//        btnDelete.setOnClickListener{
//            deleteRecord(
//                intent.getStringExtra("tipId").toString()
//            )
//        }

    }

    //deleteRecord function
    private fun deleteRecord(id: String) {
        val dbRef= FirebaseDatabase.getInstance().getReference("Tips").child(id)
        val tTask = dbRef.removeValue()

        tTask.addOnSuccessListener {
            Toast.makeText(this, "Tip deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this,FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Error while deleting tip ${error.message}", Toast.LENGTH_LONG).show()
        }

    }

    //setValuesToViews function
    private fun setValuesToViews() {
//        tvTipId.text=intent.getStringExtra("tipId")
        tvTipName.text=intent.getStringExtra("tipName")
        tvTipDescription.text=intent.getStringExtra("tipDescription")
    }

    //initView function
    private fun initView() {

//        tvTipId = findViewById(R.id.tvTipId)
        tvTipName = findViewById(R.id.tvTipName)
        tvTipDescription = findViewById(R.id.tvTipDescription)

//        btnUpdate = findViewById(R.id.btnUpdate)
//        btnDelete = findViewById(R.id.btnDelete)

    }

    //updateTipData function
    private fun updateTipData (
        id:String,
        name:String,
        description:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Tips").child(id)
        val tipInfo =  TipModel(id,name,description)
        dbRef.setValue(tipInfo)
    }
}