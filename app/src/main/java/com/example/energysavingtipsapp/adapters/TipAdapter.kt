package com.example.energysavingtipsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.energysavingtipsapp.R
import com.example.energysavingtipsapp.models.TipModel

class TipAdapter(private val tipList: ArrayList<TipModel>) :
    RecyclerView.Adapter<TipAdapter.ViewHolder>(){

    private lateinit var mListner: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tip_list_item, parent, false)
        return ViewHolder(itemView, mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTip = tipList[position]
        holder.tvTipName.text = currentTip.tipName
    }

    override fun getItemCount(): Int {
        return tipList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvTipName : TextView = itemView.findViewById(R.id.tvTipName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}