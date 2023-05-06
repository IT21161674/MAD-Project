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

class TipDetailsActivity : AppCompatActivity() {

    private lateinit var tvTipId: TextView
    private lateinit var tvTipName: TextView
    private lateinit var tvTipDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("tipId").toString(),
                intent.getStringExtra("tipName").toString()

            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("tipId").toString()
            )
        }

    }

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

    private fun setValuesToViews() {
//        tvTipId.text=intent.getStringExtra("tipId")
        tvTipName.text=intent.getStringExtra("tipName")
        tvTipDescription.text=intent.getStringExtra("tipDescription")
    }

    private fun initView() {

//        tvTipId = findViewById(R.id.tvTipId)
        tvTipName = findViewById(R.id.tvTipName)
        tvTipDescription = findViewById(R.id.tvTipDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }
    private fun openUpdateDialog(tipId: String, tipName: String) {
        val tDialog =  AlertDialog.Builder(this)
        val inflater = layoutInflater
        val pDialogView= inflater.inflate(R.layout.update_tip,null)

        tDialog.setView(pDialogView)

        val edtTipName = pDialogView.findViewById<EditText>(R.id.updateTipName)
        val edtTipDescription = pDialogView.findViewById<EditText>(R.id.updateTipDescription)
        val btnUpdate = pDialogView.findViewById<Button>(R.id.btnUpdateData)

        edtTipName.setText(intent.getStringExtra("tipName").toString())
        edtTipDescription.setText(intent.getStringExtra("tipDescription").toString())

        tDialog.setTitle("Updating $tipName Record")

        val alertDialog= tDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener {
            updateTipData (
                tipId,
                edtTipName.text.toString(),
                edtTipDescription.text.toString()
            )

            Toast.makeText(applicationContext,"Tip Updated", Toast.LENGTH_LONG).show()

            //setting updated data to our textviews
            tvTipName.text= edtTipName.text.toString()
            tvTipDescription.text= edtTipDescription.text.toString()

            alertDialog.dismiss()
        }
    }
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