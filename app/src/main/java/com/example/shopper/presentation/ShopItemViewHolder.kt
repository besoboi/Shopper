package com.example.shopper.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopper.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val tvName: TextView = view.findViewById(R.id.item_name)
    val tvCount: TextView = view.findViewById(R.id.item_count)

}