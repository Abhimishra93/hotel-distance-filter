package com.example.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.entity.Restaurant
import com.example.homework.holder.RestaurantViewHolder

class RestaurantListAdapter( ) : RecyclerView.Adapter<RestaurantViewHolder>() {

    private var datas: List<Restaurant?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant_list, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindData(datas?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    fun setDatas(pDatas: List<Restaurant?>?) {
        this.datas = pDatas
        notifyDataSetChanged()
    }

}