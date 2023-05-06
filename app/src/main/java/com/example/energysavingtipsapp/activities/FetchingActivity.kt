package com.example.energysavingtipsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.energysavingtipsapp.R
import com.example.energysavingtipsapp.adapters.TipAdapter
import com.example.energysavingtipsapp.models.TipModel
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    //defining variables
    private lateinit var tipRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var tipList: ArrayList<TipModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        tipRecyclerView = findViewById(R.id.tipRecyclerView)
        tipRecyclerView.layoutManager = LinearLayoutManager(this)
        tipRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        tipList = arrayListOf<TipModel>()

        //calling getTipData method
        getTipData()
    }

    //getTipData function
    private fun getTipData(){
        tipRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Tips")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                tipList.clear()
                if(snapshot.exists()){
                    for(tipSnap in snapshot.children){
                        val tipData = tipSnap.getValue(TipModel::class.java)
                        tipList.add(tipData!!)
                    }
                    val mAdapter = TipAdapter(tipList)
                    tipRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : TipAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity, TipDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("tipId", tipList[position].tipId)
                            intent.putExtra("tipName", tipList[position].tipName)
                            intent.putExtra("tipDescription", tipList[position].tipDescription)
                            startActivity(intent)
                        }

                    })

                    tipRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}